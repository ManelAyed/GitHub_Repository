package com.me.president;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;

@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {

	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
	
		BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, List<BlobKey>> blobs = bs.getUploads(req);
		BlobKey key = blobs.get("president-pic").get(0);
		String presUrl  = ImagesServiceFactory.getImagesService().getServingUrl(key);
		String presName = req.getParameter("president-name");
		resp.sendRedirect(presUrl); // just for debug
		// TODO: keep the name and the pic URL of the president in the datastore (using an
		// Objectify class) and use these values to display the elected president.
	}
}
