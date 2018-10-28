import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class UpPhotoServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        factory.setSizeThreshold(1024* 1024);

        List items = null;
        try {
            items = upload.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        Iterator iter = items.iterator();
        while (iter.hasNext()){
            FileItem item = (FileItem) iter.next();
            if (!item.isFormField()){
                //根据时间戳创建头像文件
                filename = System.currentTimeMillis()+".jpg";
                //通过getRealPath获取上传文件夹，如果项目在e://
                String photoFolder = req.getServletContext().getRealPath("/")+"uploaded";
                //String photoFolder = "C:\\Users\\lmh\\Desktop\\未标题3-jpg";
                File f = new File(photoFolder,filename);
                f.getParentFile().mkdirs();

                //通过item.getInputStream()获取浏览器上传的文件输入流
                InputStream is = item.getInputStream();
                FileOutputStream fos = new FileOutputStream(f);
                byte b[] = new byte[1024* 1024];
                int length = 0;
                while (-1 != (length = is.read(b))){
                    fos.write(b,0,length);
                }
                fos.close();
            }else {
                System.out.println(item.getFieldName());
                String value = item.getString();
                value = new String(value.getBytes("ISO-8859-1"),"utf-8");
                System.out.println(value);
            }
        }
        String html = "<img width='200' height='150' src='upload/%s' />";
        resp.setContentType("text/html,charset=UTF-8");
        resp.getWriter().format(html,filename);

    }
}
