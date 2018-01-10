import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import br.MagicToys.dao.BrinquedoDAO;
import br.MagicToys.model.Brinquedo;

/**
 * Servlet implementation class ReqBrinquedos
 */
@WebServlet("/ExcluirBrinquedo")
@MultipartConfig
public class ExcluirBrinquedo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int codigo =0;
			String erro ="";
			String msg;
			
			if (request.getParameter("codigo").equals("") || request.getParameter("codigo") == null)
				erro += "Precisa preencher a codigo. <br>";
			else
			{
				codigo = Integer.parseInt(request.getParameter("codigo").replace(".", "").replace(",", "."));
				if (codigo ==0)
					erro += "Precisa preencher o codigo. <br>";
			}
			
			
			
			if (erro == "" )
			{
				
				
				BrinquedoDAO excluir = new BrinquedoDAO(codigo);
				boolean retorno =false;
				retorno = excluir.ExcluirBrinquedo();
				
				if (retorno)
				{
					msg = "Brinquedo excluido com sucesso!";
				}
				else
				{
					msg = "Houve um problema ao excluir o brinquedo, por favor verifique.";
				}
			}
			else
			{
				msg = erro;
			}
			response.sendRedirect("TelaInicial.jsp?msg=" + msg);
		}
		catch (Exception e)
		{
			PrintWriter out = response.getWriter();
			out.println("Ocorreu um problema ao excluir o brinquedo <br>"+ e.getMessage());
		}
			
	}

}
