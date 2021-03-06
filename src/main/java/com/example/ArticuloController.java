package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.net.URISyntaxException;
import com.example.UserJDBCTemplate;

import java.io.File;
import java.io.IOException;
import java.lang.Integer;
import java.lang.String;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticuloController {

    //Publish.html

    @GetMapping("/Publish")
    public String articuloForm(Model model, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
    throws URISyntaxException, SQLException {   
        Connection conn = Main.getConnection();
        UsuarioJDBCTemplate articuloTemplate = new UsuarioJDBCTemplate();   
        articuloTemplate.setDataSource(conn);   

        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        usuario.setNombreUsuario("Login To get started");
        usuario.setId(14);

        loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");

        if(loggedUsuario != null)
            usuario = loggedUsuario;

        model.addAttribute("usuario", usuario);
        
        if (!conn.isClosed()) 
            conn.close();


        model.addAttribute("articulo", new Articulo());  

        return "Publish";
    }

    @PostMapping("/publishArticulo") 
    public String publish( @ModelAttribute Articulo articulo,
    BindingResult bindingResult, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember,
    @RequestParam(value = "nombreArticulo", required = false) String nombreArticulo,
    @RequestParam(value = "tipo", required = true) Integer tipo,
    @RequestParam(value = "region", required = true) Integer region,
    @RequestParam(value = "image_1", required = false) MultipartFile image_1,
    @RequestParam(value = "image_2", required = false) MultipartFile image_2,
    @RequestParam(value = "image_3", required = false) MultipartFile image_3,
    @RequestParam(value = "video", required = false) MultipartFile video,
    @RequestParam(value = "saveMe", required = false) String save,
    HttpServletRequest request)
    throws URISyntaxException, SQLException {    
        Connection conn = Main.getConnection();  
       
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().stream()
            .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        }
        try{
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();

            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;
            else
                throw new Exception("You must be logged in as a user to publish a product.");

            Articulo_Categoria articuloTipo = new Articulo_Categoria();
            articuloTipo.setIdCategoria(tipo);

            Articulo_Categoria articuloRegion = new Articulo_Categoria();
            articuloRegion.setIdCategoria(region);

            articulo.setNombre(nombreArticulo);

            articulo.setImagen_1(image_1.getBytes());
            articulo.setImagen_2(image_2.getBytes());
            articulo.setImagen_3(image_3.getBytes());

            articulo.setActivo(1);
            articulo.setVisitas(0);
            articulo.setOferta(0);

            if (save != null){
                articulo.setPublico(0);
            }
            else{
                articulo.setPublico(1);
            }

            articulo.setVideo("");

            if(!video.isEmpty()){
                Articulo ultimoArticulo = articuloTemplate.getLastArticulo();

                Integer lastInteger;
                if(ultimoArticulo != null){
                    lastInteger = ultimoArticulo.getId() + 1;

                    ServletContext context = request.getServletContext();
                    String path = context.getRealPath("/");
                    File file = new File (path, lastInteger.toString()+".mp4");
                    video.transferTo(file);
                    articulo.setVideo(path);
                }
                else
                    return "error/" + "Something is wrong with id of articulo";
            }

            articulo.setIdUsuario(usuario.getId());

            articuloTemplate.create(articulo, articuloTipo, articuloRegion);    
        }
        catch (Exception ex) {
            if (!conn.isClosed()) 
                conn.close();
            return "error/" + ex;
        }
        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/"; 
    }

    //ItemSpotlight.html

    @PostMapping("/product__info") 
    public String productInfo(Model model, @ModelAttribute Articulo articulo,
    @RequestParam(value = "ProductId", required = false) Integer ID_Articulo)
    throws URISyntaxException, SQLException {      

        return "redirect:/item/"+ ID_Articulo.toString(); 
    }

    @GetMapping("/item/{ID_Articulo}")
    public String userId(
        Model model,  HttpSession session, 
        @PathVariable(value="ID_Articulo") final Integer ID_Articulo,
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember)
        throws URISyntaxException, SQLException {

            Connection conn = Main.getConnection();  

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   

            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();
            articuloTemplate.setDataSource(conn);

            ComentarioJDBCTemplate comentarioTemplate = new ComentarioJDBCTemplate();   
            comentarioTemplate.setDataSource(conn); 
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);

            model.addAttribute("usuario", usuario);

            List<Comentario> comentarios = comentarioTemplate.getComentariosOwnedByArticulo(ID_Articulo);
            model.addAttribute("comments", comentarios);

            if(articulo != null){
                model.addAttribute("articulo", articulo);

                
                if (!conn.isClosed()) 
                    conn.close();

                return "itemSpotlight";
                //return "redirect:/item";
            }

            
            if (!conn.isClosed()) 
            conn.close();
            
            return "redirect:/";
    }

    @PostMapping("/addToKart") 
    public String addToKart( HttpSession session, 
    @RequestParam(value = "unidades", required = true) Integer unidades,
    @RequestParam(value = "ProductId", required = true) Integer ID_Articulo)
    throws URISyntaxException, SQLException {      
        Connection conn = Main.getConnection();  

        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
        usuarioTemplate.setDataSource(conn);   
        
        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        usuario.setNombreUsuario("Login to get started");
        usuario.setId(14);

        loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");

        if(loggedUsuario != null)
            usuario = loggedUsuario;

        Carrito carrito = new Carrito();

        carrito.setIdUsuario(usuario.getId());
        carrito.setIdArticulo(ID_Articulo);
        carrito.setUnidades(unidades);

        CarritoJDBCTemplate carritoTemplate = new CarritoJDBCTemplate();
        carritoTemplate.setDataSource(conn);   

        carritoTemplate.addToKart(carrito);

        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/item/"+ ID_Articulo.toString();
    }

    @PostMapping("/publishComment") 
    public String publishComment( HttpSession session, 
    @RequestParam(value = "ProductId", required = true) Integer ID_Articulo,
    @RequestParam(value = "commentString", required = false) String commentString)
    throws URISyntaxException, SQLException {      
        Connection conn = Main.getConnection();  

        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
        usuarioTemplate.setDataSource(conn);   
        
        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        usuario.setNombreUsuario("Login to get started");
        usuario.setId(14);

        loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");

        if(loggedUsuario != null)
            usuario = loggedUsuario;

        Comentario comentario = new Comentario();

        comentario.setIdUsuario(usuario.getId());
        comentario.setIdArticulo(ID_Articulo);
        comentario.setComentario(commentString);

        ComentarioJDBCTemplate comentarioTemplate = new ComentarioJDBCTemplate();
        comentarioTemplate.setDataSource(conn);   

        comentarioTemplate.publishComment(comentario);

        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/item/"+ ID_Articulo.toString();
    }

   

    //Search.html

    @PostMapping("/search__product") 
    public String searchProduct(
    @RequestParam(value = "searchString", required = false) String searchString)
    throws URISyntaxException, SQLException {      

        return "redirect:/Search/"+ searchString; 
    }

    @GetMapping("/Search/{searchString}")
    public String showSearch(Model model,  HttpSession session, 
        @PathVariable(value="searchString") final String searchString,
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember)
        throws URISyntaxException, SQLException {

            Connection conn = Main.getConnection();  

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();
            articuloTemplate.setDataSource(conn);

            List<Articulo> articulosNombre = articuloTemplate.getArticuloLikeNombre(searchString);
            List<Articulo> articulosDescription = articuloTemplate.getArticuloLikeDescripcion(searchString);
            List<Articulo> articulosCategoria = articuloTemplate.getArticuloLikeCategoria(searchString);
            List<Articulo> articulosUsuario = articuloTemplate.getArticuloLikeUsuario(searchString);
            List<Articulo> articulosEstampaTiempo = articuloTemplate.getArticuloLikeEstampaTiempo(searchString);

            model.addAttribute("usuario", usuario);
            model.addAttribute("byNameResults", articulosNombre);
            model.addAttribute("byDescriptionResults", articulosDescription);
            model.addAttribute("byCategoryResults", articulosCategoria);
            model.addAttribute("byUserResults", articulosUsuario);
            model.addAttribute("byTimestampResults", articulosEstampaTiempo);

            
            if (!conn.isClosed()) 
            conn.close();

            return "Search";
    }

    //Catalog.html

    @GetMapping("/Catalog/{ID_Categoria}")
    public String showCatalog(Model model,  HttpSession session, 
        @PathVariable(value="ID_Categoria") final Integer ID_Categoria,
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember)
        throws URISyntaxException, SQLException {

            Connection conn = Main.getConnection();  

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();
            articuloTemplate.setDataSource(conn);

            CategoriaJDBCTemplate categoriaTemplate = new CategoriaJDBCTemplate();
            categoriaTemplate.setDataSource(conn);

            Categoria categoria = categoriaTemplate.getCategoria(ID_Categoria);

            List<Articulo> articulos = articuloTemplate.listArticulo(categoria.getId());

            model.addAttribute("usuario", usuario);
            model.addAttribute("category", categoria);
            model.addAttribute("categoryResults", articulos);

            if (!conn.isClosed()) 
            conn.close();

            return "Catalog";
    }

    //Queue.html

    @GetMapping("/Queue")
    public String Homecoming(Model model, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
    throws URISyntaxException, SQLException {
        Connection conn = Main.getConnection();
        UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
        usuarioTemplate.setDataSource(conn);   

        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);   
        
        Usuario usuario;
        Usuario loggedUsuario;

        usuario = new Usuario();

        usuario.setNombreUsuario("Login to get started");
        usuario.setId(14);

        loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");

        if(loggedUsuario != null)
            usuario = loggedUsuario;

        List<Articulo> articulos = articuloTemplate.getArticulosOwnedByUsuario(usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("articulos", articulos);

        if (!conn.isClosed()) 
            conn.close();

        return "Queue";
    }

    @PostMapping(value = "/QueueSelect", params = "action=edit")
    public String queueEdit( 
        @RequestParam(value = "ProductId", required = true) Integer ID_Articulo)
        throws URISyntaxException, SQLException {      
    
        return "redirect:/Edit/"+ ID_Articulo.toString(); 
    }

    @PostMapping(value = "/QueueSelect", params = "action=publish")
    public String queuePublish(
    @RequestParam(value = "ProductId", required = true) Integer ID_Articulo)
    throws URISyntaxException, SQLException {

        Connection conn = Main.getConnection();

        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);   

        articuloTemplate.publishArticulo(ID_Articulo);

        if (!conn.isClosed()) 
        conn.close();

        return "redirect:/";
    }

    @PostMapping(value = "/QueueSelect", params = "action=delete")
    public String editDelete(
    @RequestParam(value = "ProductId", required = true) Integer ID_Articulo)
    throws URISyntaxException, SQLException {

        Connection conn = Main.getConnection();

        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);   

        articuloTemplate.deleteArticulo(ID_Articulo);

        if (!conn.isClosed()) 
        conn.close();

        return "redirect:/";
    }

    //Edit.html

    @GetMapping("/Edit/{ID_Articulo}")
    public String editProduct(Model model,  HttpSession session, 
        @PathVariable(value="ID_Articulo") final Integer ID_Articulo,
        @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember)
        throws URISyntaxException, SQLException {

            Connection conn = Main.getConnection();  

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();
            articuloTemplate.setDataSource(conn);

            Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);

            model.addAttribute("usuario", usuario);
            model.addAttribute("product", articulo);

            if (!conn.isClosed()) 
            conn.close();    

            return "Edit";
    }

    @PostMapping("/EditProduct") 
    public String rePublish( @ModelAttribute Articulo articulo,
    BindingResult bindingResult, HttpServletResponse response, HttpSession session, 
    @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember,
    @RequestParam(value = "ProductId", required = true) Integer ID_Articulo,
    @RequestParam(value = "nombreArticulo", required = false) String nombreArticulo,
    @RequestParam(value = "descripcion", required = false) String descripcion,
    @RequestParam(value = "tipo", required = true) Integer tipo,
    @RequestParam(value = "region", required = true) Integer region,
    @RequestParam(value = "image_1", required = false) MultipartFile image_1,
    @RequestParam(value = "image_2", required = false) MultipartFile image_2,
    @RequestParam(value = "image_3", required = false) MultipartFile image_3,
    @RequestParam(value = "video", required = false) MultipartFile video,
    @RequestParam(value = "precio", required = true) Float precio,
    @RequestParam(value = "unidades", required = true) Integer unidades,
    @RequestParam(value = "oferta", required = true) Integer oferta,
    @RequestParam(value = "saveMe", required = false) String save,
    HttpServletRequest request)
    throws URISyntaxException, SQLException {    
        Connection conn = Main.getConnection();  
       
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().stream()
            .forEach(f -> System.out.println(f.getField() + ": " + f.getDefaultMessage()));
        }
        try{
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   

            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();

            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;
            else
                throw new Exception("You must be logged in as a user to publish a product.");

            Articulo_Categoria articuloTipo = new Articulo_Categoria();
            articuloTipo.setIdCategoria(tipo);

            Articulo_Categoria articuloRegion = new Articulo_Categoria();
            articuloRegion.setIdCategoria(region);

            articulo.setId(ID_Articulo);
            articulo.setNombre(nombreArticulo);

            articulo.setImagen_1(image_1.getBytes());
            articulo.setImagen_2(image_2.getBytes());
            articulo.setImagen_3(image_3.getBytes());

            articulo.setDescripcion(descripcion);
            articulo.setPrecio(precio);
            articulo.setUnidades(unidades);
            articulo.setOferta(oferta);

            articulo.setActivo(1);
            articulo.setVisitas(0);
            articulo.setOferta(0);

            if (save != null){
                articulo.setPublico(0);
            }
            else{
                articulo.setPublico(1);
            }

            articulo.setVideo("");

            if(!video.isEmpty()){
                    ServletContext context = request.getServletContext();
                    String path = context.getRealPath("/");
                    File file = new File (path, ID_Articulo.toString()+".mp4");
                    video.transferTo(file);
                    articulo.setVideo(path);
            }

            articulo.setIdUsuario(usuario.getId());

            articuloTemplate.update(articulo, articuloTipo, articuloRegion);    
        }
        catch (Exception ex) {
            if (!conn.isClosed()) 
                conn.close();
            return "error/" + ex;
        }
        if (!conn.isClosed()) 
            conn.close();

        return "redirect:/"; 
    }

    //Image loaders

    @GetMapping("/ImgArticulo1/{ID_Articulo}") 
    public void imageArt1(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_1());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

    @GetMapping("/ImgArticulo2/{ID_Articulo}") 
    public void imageArt2(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_2());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

    @GetMapping("/ImgArticulo3/{ID_Articulo}") 
    public void imageArt3(HttpServletResponse response,  
    @PathVariable(value="ID_Articulo") final Integer ID_Articulo)  
    throws URISyntaxException, SQLException, IOException {  

        Connection conn = Main.getConnection();
        ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
        articuloTemplate.setDataSource(conn);    

        Articulo articulo = articuloTemplate.getArticulo(ID_Articulo);      
        response.setContentType( "image/jpeg, image/jpg, image/png, image/gif");   
            response.getOutputStream().write(articulo.getImagen_2());
            response.getOutputStream().close(); 

        if (!conn.isClosed()) 
            conn.close();
    } 

      //Kart.html

      @GetMapping("/Kart")
      public String kart(Model model, HttpServletResponse response, HttpSession session, 
      @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
      throws URISyntaxException, SQLException {
          Connection conn = Main.getConnection();
          UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
          usuarioTemplate.setDataSource(conn);   
  
          ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
          articuloTemplate.setDataSource(conn);   
          
          Usuario usuario;
          Usuario loggedUsuario;
  
          usuario = new Usuario();
  
          usuario.setNombreUsuario("Login to get started");
          usuario.setId(14);
  
          loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
  
          if(loggedUsuario != null)
              usuario = loggedUsuario;
  
          List<Articulo> articulos = articuloTemplate.getArticuloKartedByUsuario(usuario.getId());
  
          model.addAttribute("usuario", usuario);
          model.addAttribute("articulos", articulos);
  
          if (!conn.isClosed()) 
              conn.close();
  
          return "Kart";
      }

      @PostMapping(value = "/KartSelect", params = "action=buy")
      public String kartBuy( HttpSession session,
          @RequestParam(value = "unidades", required = true) Integer unidades,
          @RequestParam(value = "ProductId", required = true) Integer ID_Articulo,
          @RequestParam(value = "KartId", required = true) Integer ID_Carrito)
          throws URISyntaxException, SQLException {   
              
            Connection conn = Main.getConnection();
            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
    
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            Carrito carrito = new Carrito();
            carrito.setId(ID_Carrito);
            carrito.setUnidades(unidades);

            articuloTemplate.purchaseArticulo(usuario.getId(), ID_Articulo, carrito);

            if (!conn.isClosed()) 
            conn.close();
      
          return "redirect:/Kart"; 
      }

      @PostMapping(value = "/KartSelect", params = "action=remove")
      public String kartRemove(
          @RequestParam(value = "KartId", required = true) Integer ID_Carrito)
          throws URISyntaxException, SQLException {   
              
            Connection conn = Main.getConnection();

            CarritoJDBCTemplate carritoTemplate = new CarritoJDBCTemplate();

            carritoTemplate.delete(ID_Carrito);

            if (!conn.isClosed()) 
            conn.close();
      
          return "redirect:/Kart"; 
      }

      //History.html

      @GetMapping("/History")
      public String history(Model model, HttpServletResponse response, HttpSession session, 
      @CookieValue(value = "cookie_Remember", defaultValue ="") String cookieRemember) 
      throws URISyntaxException, SQLException {
          Connection conn = Main.getConnection();
          UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
          usuarioTemplate.setDataSource(conn);   
  
          ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
          articuloTemplate.setDataSource(conn);   
          
          Usuario usuario;
          Usuario loggedUsuario;
  
          usuario = new Usuario();
  
          usuario.setNombreUsuario("Login to get started");
          usuario.setId(14);
  
          loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
  
          if(loggedUsuario != null)
              usuario = loggedUsuario;
  
          List<Articulo> articulos = articuloTemplate.getArticuloBoughtByUsuario(usuario.getId());
  
          model.addAttribute("usuario", usuario);
          model.addAttribute("articulos", articulos);
  
          if (!conn.isClosed()) 
              conn.close();
  
          return "History";
      }

      @PostMapping(value = "/HistoryRate", params = "action=rate")
      public String sistoryRate( HttpSession session,
          @RequestParam(value = "star1", required = false) String s1, 
          @RequestParam(value = "star2", required = false) String s2, 
          @RequestParam(value = "star3", required = false) String s3, 
          @RequestParam(value = "star4", required = false) String s4, 
          @RequestParam(value = "star5", required = false) String s5, 
          @RequestParam(value = "ProductId", required = true) Integer ID_Articulo)
          throws URISyntaxException, SQLException {   
              
            Connection conn = Main.getConnection();
            UsuarioJDBCTemplate usuarioTemplate = new UsuarioJDBCTemplate();   
            usuarioTemplate.setDataSource(conn);   
    
            ArticuloJDBCTemplate articuloTemplate = new ArticuloJDBCTemplate();   
            articuloTemplate.setDataSource(conn);   

            Articulo articulo = new Articulo();

            articulo.setId(ID_Articulo);
            
            Usuario usuario;
            Usuario loggedUsuario;
    
            usuario = new Usuario();
    
            usuario.setNombreUsuario("Login to get started");
            usuario.setId(14);
    
            loggedUsuario = (Usuario) session.getAttribute("loggedUsuario");
    
            if(loggedUsuario != null)
                usuario = loggedUsuario;

            articulo.setVisitas(0);

            if (s5 != null)
                articulo.setVisitas(5);

            if (s4 != null)
                articulo.setVisitas(4);
            
            if (s3 != null)
                articulo.setVisitas(3);

            if (s2 != null)
                articulo.setVisitas(2);    
            
            if (s1 != null)
                articulo.setVisitas(1);  

            articuloTemplate.rateArticulo(articulo);

            if (!conn.isClosed()) 
            conn.close();
      
            return "redirect:/item/"+ ID_Articulo.toString();
      }

}