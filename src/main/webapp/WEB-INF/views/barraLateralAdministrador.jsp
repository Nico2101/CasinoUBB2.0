<%-- 
    Document   : barraLateral
    Created on : 15-sep-2017, 22:40:52
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="nav nav-list">
    <li class="active">
        <a href="indexAdministrador.htm">
            <i class="menu-icon fa fa-home"></i>
            <span class="menu-text"> Inicio </span>
        </a>

        <b class="arrow"></b>
    </li>

    <li class="">
    	
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon glyphicon glyphicon-cutlery"></i>
            <span class="menu-text"> Menú </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="">
                <a href="ingresarMenu.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Ingresar Menú
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="buscarMenu2.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Modificar Menú
                </a>

                <b class="arrow"></b>
            </li>
        </ul>
    </li>

    
    

</ul><!-- /.nav-list -->
