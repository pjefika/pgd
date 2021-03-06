package controllers;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import entidades.ControleUsuario;
import entidades.UsuarioEfika;
import models.LoginServico;
import util.JSFUtil;
import webservices.Usuario;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private UsuarioEfika usuario;

    private Usuario usuarioWS;

    private String senha;

    private String pagina;

    @EJB
    private LoginServico servicoLogin;

    private boolean logado;

    public LoginBean() {
        this.usuario = new UsuarioEfika();
        this.logado = false;
    }

    public void validarLogin() {

        FacesContext fc = FacesContext.getCurrentInstance();

        if (!this.logado) {
            
            this.geturl();

            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
            nav.performNavigation("index.jsf");
        }

    }
    
    public void geturl() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        
    }

    public Boolean is_Admin(String sistema) {

        ControleUsuario ControleUsuario = new ControleUsuario();

        String[] sistemaBuscado;

        Boolean esAdm = false;

        try {

            ControleUsuario = this.servicoLogin.validaListaAdm(this.usuario);

            sistemaBuscado = ControleUsuario.getSistema().split(";");

            for (String string : sistemaBuscado) {

                if (string.equalsIgnoreCase(sistema)) {

                    esAdm = true;

                }

            }

            return esAdm;

        } catch (Exception e) {

            return esAdm;

        }

    }

    public void validaAdmin(String sistema) {

        try {

            this.validarLogin();

            FacesContext fc = FacesContext.getCurrentInstance();

            if (!this.is_Admin(sistema)) {

                ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) fc.getApplication().getNavigationHandler();
                nav.performNavigation("restrito.jsf");

            }
        } catch (Exception e) {

            this.usuario = new UsuarioEfika();

        }

    }

    public String logar() {
        
        try {

            this.usuarioWS = this.servicoLogin.buscaLoginWS(this.usuario.getLogin());
            this.servicoLogin.autenticaLogin(this.usuarioWS, this.senha);

            this.logado = true;            

            return "index.jsf";

        } catch (Exception e) {

            JSFUtil.addErrorMessage(e.getMessage());
            this.usuario = new UsuarioEfika();

            return "index.jsf";

        }

    }

    public void deslogar() {

        this.usuario = new UsuarioEfika();
        this.logado = false;

    }

    public void validaPagina(String pagina) {

        this.pagina = pagina;

    }

    public UsuarioEfika getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEfika usuario) {
        this.usuario = usuario;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuarioWS() {
        return usuarioWS;
    }

    public void setUsuarioWS(Usuario usuarioWS) {
        this.usuarioWS = usuarioWS;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

}
