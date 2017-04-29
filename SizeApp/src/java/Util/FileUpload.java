/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

@ManagedBean
@ViewScoped
public class FileUpload implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int BUFFER_SIZE = 1024;

    
    public String uploadFile(Part file1, String tipo, String imagemNome) throws IOException {
        File outputFile = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context
                .getExternalContext().getContext();
        String path = servletContext.getRealPath("");
        
        if (file1.getSize() > 0) {
            // String fileName = getFileNameFromPart(file1);
          
            // destino do arquivo
            if(tipo.equals("estabelecimento")){
            outputFile = new File(path + File.separator + "\\WebContent\\imagens\\estabelecimento"
                    + File.separator + imagemNome);
            }else if (tipo.equals("usuario")){
            outputFile = new File("C:\\Users\\Lucas\\Documents\\NetBeansProjects\\tcc\\SizeApp\\web\\WebContent\\imagens\\usuario\\" + imagemNome);            
            }
            inputStream = file1.getInputStream();
            
            outputStream = new FileOutputStream(outputFile);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = 0;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }

        }
        
        
  
        
        return null;
    }
    
        public static String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1)
                        .trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }
}
