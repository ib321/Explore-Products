<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
  String savePath = "C:/path/to/save/images"; // replace with the path to the directory where you want to save images
  File fileSaveDir = new File(savePath);
  if (!fileSaveDir.exists()) {
    fileSaveDir.mkdir();
  }
  Part filePart = request.getPart("imageFile");
  // fileName = getFileName(filePart);
  String fileName = "new";
  File file = new File(savePath + File.separator + fileName);
  try (InputStream inputStream = filePart.getInputStream();
    OutputStream outputStream = new FileOutputStream(file)) {
    int read;
    final byte[] bytes = new byte[1024];
    while ((read = inputStream.read(bytes)) != -1) {
      outputStream.write(bytes, 0, read);
    }
    out.println("File " + fileName + " has been uploaded and saved to " + savePath);
  } catch (Exception e) {
    out.println("Error: " + e.getMessage());
  }
  // method to get the file name from the Part object
/*   private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    for (String content : partHeader.split(";")) {
      if (content.trim().startsWith("filename")) {
        return content.substring(content.indexOf('=') + 1).trim()
            .replace("\"", "");
      }
    }
    return null;
  } */
%>
