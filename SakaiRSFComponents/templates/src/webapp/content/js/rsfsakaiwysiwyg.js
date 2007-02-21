// function chef_setupformattedtextarea(textarea_id)
function setupRSFFormattedTextarea(textarea_id, collection_id)
{
	var oFCKeditor = new FCKeditor(textarea_id);
	oFCKeditor.BasePath = "/library/editor/FCKeditor/";

	//var collection_id = "/group/9dd219e5-d840-42c9-803e-2c02b4ce69a4/";

	oFCKeditor.Config['ImageBrowserURL'] = oFCKeditor.BasePath + 
	  "editor/filemanager/browser/default/browser.html?Connector=/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector&Type=Image&CurrentFolder=" + collection_id;
	oFCKeditor.Config['LinkBrowserURL'] = oFCKeditor.BasePath + 
	  "editor/filemanager/browser/default/browser.html?Connector=/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector&Type=Link&CurrentFolder=" + collection_id;
	oFCKeditor.Config['FlashBrowserURL'] = oFCKeditor.BasePath + 
	  "editor/filemanager/browser/default/browser.html?Connector=/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector&Type=Flash&CurrentFolder=" + collection_id;
	oFCKeditor.Config['ImageUploadURL'] = oFCKeditor.BasePath + 
	  "/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector?Type=Image&Command=QuickUpload&Type=Image&CurrentFolder=" + collection_id;
	oFCKeditor.Config['FlashUploadURL'] = oFCKeditor.BasePath + 
	  "/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector?Type=Flash&Command=QuickUpload&Type=Flash&CurrentFolder=" + collection_id;
	oFCKeditor.Config['LinkUploadURL'] = oFCKeditor.BasePath + 
	  "/sakai-fck-connector/web/editor/filemanager/browser/default/connectors/jsp/connector?Type=File&Command=QuickUpload&Type=Link&CurrentFolder=" + collection_id;
//	oFCKeditor.Width  = "600" ;
//	oFCKeditor.Height = "400" ;
	oFCKeditor.Config['CustomConfigurationsPath'] = "/library/editor/FCKeditor/config.js";
	oFCKeditor.ReplaceTextarea() ;
}