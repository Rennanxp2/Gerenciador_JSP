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
@WebServlet("/InserirBrinquedo")
@MultipartConfig
public class InserirBrinquedo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String erro ="";
			String msg;
			Brinquedo brinquedo = new Brinquedo();
			String descricao;
			String marca;
			String categoria;
			String imagem ="";
			double valor =0;
			String detalhes;
			PrintWriter out = response.getWriter();
			Part filePart = request.getPart("imagem"); // Retrieves <input type="file" name="imagem">
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			out.println(fileName);
			if (!fileName.equals(""))
			{
				fileName = LocalDateTime.now().toString().replace("/", "").replace(":", "").replace(" ", "").replace("-", "").replace(".", "")+ fileName.replaceAll(" ",  "");
			    InputStream fileContent = filePart.getInputStream();
			    File upload = new File("C:\\Users\\Rennan Zaniboni\\Documents\\Tecnologia da Informação\\CatalogoBrinquedos\\CatalogoBriquedosAdministracao\\WebContent\\imagensBrinquedos");
			    File file = new File(upload, fileName);
			    Files.copy(fileContent, file.toPath());
			    imagem = fileName;
			}
			if (imagem.equals("")  || imagem == null)
				erro += "Precisa enviar uma imagem. <br>";
			
			descricao = request.getParameter("descricao");
			if (descricao.equals("")  || descricao == null)
				erro += "Precisa preencher a desricao. <br>";

			marca = request.getParameter("marca");
			if (marca.equals("") || marca == null)
				erro += "Precisa preencher a marca. <br>";
			
			categoria = request.getParameter("categoria");
			if (categoria.equals("") || categoria == null)
				erro += "Precisa preencher a categoria. <br>";
			
			if (request.getParameter("valor").equals("") || request.getParameter("valor") == null)
				erro += "Precisa preencher o valor.";
			else
			{
				valor = Double.parseDouble(request.getParameter("valor").replace(".", "").replace(",", "."));
				if (valor ==0)
					erro += "Precisa preencher o valor. <br>";
			}
			
			detalhes = request.getParameter("detalhes");
			if (detalhes.equals("") || detalhes == null)
				erro += "Precisa preencher os detalhes. <br>";
			
			if (erro == "" )
			{
				brinquedo.setDescricao(descricao);
				brinquedo.setMarca(marca);
				brinquedo.setCategoria(categoria);
				brinquedo.setImagem(imagem);
				brinquedo.setValor(valor);
				brinquedo.setDetalhes(detalhes);
				
				BrinquedoDAO inserir = new BrinquedoDAO(brinquedo);
				boolean retorno =false;
				retorno = inserir.InserirBrinquedo();
				
				if (retorno)
				{
					msg = "Brinquedo cadastrado com sucesso";
				}
				else
				{
					msg = "Houve um problema ao cadastrar o brinquedo, por favor verifique.";
				}
			}
			else
			{
				msg = erro;
			}
			response.sendRedirect("InserirBrinquedo.jsp?msg=" + msg);
		}
		catch (Exception e)
		{
			PrintWriter out = response.getWriter();
			out.println("Ocorreu um problema ao inserir o brinquedo <br>"+ e.getMessage());
		}
			
	}

}
