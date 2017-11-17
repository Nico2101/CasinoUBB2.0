<%-- 
    Document   : barraLateral
    Created on : 15-sep-2017, 22:40:52
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="nav nav-list">
    <li class="active">
        <a href="indexUsuario.htm">
            <i class="menu-icon fa fa-home"></i>
            <span class="menu-text"> Inicio </span>
        </a>

        <b class="arrow"></b>
    </li>

   

    <li class="">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon glyphicon glyphicon-ice-lolly-tasted"></i>
            <span class="menu-text"> Reserva </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="">
                <a href="buscarMenu.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Reservar Menú
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="verReservas.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                   Ver Reservas
                </a>

                <b class="arrow"></b>
            </li>
            
        </ul>
    </li>
    
    <li class="">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon glyphicon glyphicon-check"></i>
            <span class="menu-text"> Servicio </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="">
                <a href="listarMenusComprados.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Evaluar Servicio
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="#">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Mis Evaluaciones
                </a>

                <b class="arrow"></b>
            </li>
            
        </ul>
    </li>
    
    <li class="">
        <a href="#" class="dropdown-toggle">
            <i class="menu-icon glyphicon glyphicon-file"></i>
            <span class="menu-text"> Ticket </span>

            <b class="arrow fa fa-angle-down"></b>
        </a>

        <b class="arrow"></b>

        <ul class="submenu">
            <li class="">
                <a href="generarTicket.htm">
                    <i class="menu-icon fa fa-caret-right"></i>
                    Generar Ticket
                </a>

                <b class="arrow"></b>
            </li>

            
            
        </ul>
    </li>
    
    

</ul><!-- /.nav-list -->
