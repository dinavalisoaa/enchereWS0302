<!DOCTYPE html>
<html lang="en">
    <%@page import="model.*" %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
        <title>Dashio - Bootstrap Admin Template</title>

        <!-- Favicons -->
        <link href="../img/favicon.png" rel="icon">
        <link href="../img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Bootstrap core CSS -->
        <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!--external css-->
        <link href="../lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <!-- Custom styles for this template -->
        <link href="../css/style.css" rel="stylesheet">
        <link href="../css/style-responsive.css" rel="stylesheet">

        <!-- =======================================================
          Template Name: Dashio
          Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
          Author: TemplateMag.com
          License: https://templatemag.com/license/
        ======================================================= -->
    </head>                        <% DemandeRechargement[]cor=(DemandeRechargement[])request.getAttribute("demande");%>

    <body>
        <section id="container">
            <!-- **********************************************************************************************************************************************************
                TOP BAR CONTENT & NOTIFICATIONS
                *********************************************************************************************************************************************************** -->
            <!--header start-->
              <header class="header black-bg">
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
                </div>
                <!--logo start-->
                <a href="index.html" class="logo"><b>EN<span>CHERE</span></b></a>
                <!--logo end-->
                <div class="nav notify-row" id="top_menu">
                </div>
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li>
                            <a class="logout" href="login.html">Logout</a>
                        </li>
                    </ul>
                </div>
            </header>
            <!--header end-->
            <!-- **********************************************************************************************************************************************************
                MAIN SIDEBAR MENU
                *********************************************************************************************************************************************************** -->
            <!--sidebar start-->
           <aside>
                <div id="sidebar" class="nav-collapse ">
                    <!-- sidebar menu start-->
                    <ul class="sidebar-menu" id="nav-accordion">
                        

                        <li class="sub-menu">
                            <a class="panel" href="allcategorie">
                                <i class="fa fa-desktop"></i>
                                <span>Tableau de bord</span>
                            </a>
                        </li>
                          <li class="mt">
                             <a class="panel" href="alldemandes">
                                <i class="fa fa-check-circle"></i>
                                <span>Demandes</span>
                            </a>
                        </li>
                        <li class="mt">
                             <a class="panel" href="allstatistiques">
                                <i class="fa fa-calculator"></i>
                                <span>Statistique</span>
                            </a>
                        </li>
                         <li class="mt">
                             <a class="panel" href="allparametrages">
                                <i class="fa fa-sellsy"></i>
                                <span>Parametrages</span>
                            </a>
                        </li>
                    </ul>
                    <!-- sidebar menu end-->
                </div>
            </aside>
            <!--sidebar end-->
            <!-- **********************************************************************************************************************************************************
                MAIN CONTENT
                *********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section id="main-content">
                <section class="wrapper">
                    <h3><i class="fa fa-angle-right"></i>Demandes</h3>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-panel">
                                <div class="showback">
                                    <a href="alldemandes?etat=-2">  
                                        <button type="button" class="btn btn-facebook"><i class="fa fa-table"></i>Tous Demande</button>
                                    </a>
                                   
                                    <a href="alldemandes?etat=11">  
                                        <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i>Demande refuser</button>
                                    </a>
                                    <a href="alldemandes?etat=1">  
                                        <button type="button" class="btn btn-success"><i class="fa fa-check-square"></i>Demande Valider</button>
                                    </a>
                                   
                                    </div>
                                <form action="demandes" method="get">

                                    <div class="showback">
                                        <h4><i class="fa fa-angle-right"></i><%=request.getAttribute("title")%></h4>
                                        <div class="btn-group">
                                            <div class="btn-group">

                                                <select class="form-control" style="width:250px" name="etat">
                                                    <option value="-2">Tous</option>
                                                    <option value="0">En Attente</option>
                                                    <option value="11">Refuser</option>
                                                    <option value="1">Valider</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Voir</button>
                                        </div>
                                    </div>                                    </form>



                            </div>
                            <!-- Modal -->
                            <!-- Modal -->

                            <% for(int i=0;i<cor.length;i++){%>
                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="del<%=cor[i].getId()%>" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">Refuser la demande</h4>
                                        </div>
                                        <div class="modal-body">                
                                            <form action="refuserdemande" method="get">
                                                <input type="hidden" name="id"value="<%=cor[i].getId()%>">
                                                </div>
                                                <div class="modal-footer">
                                                    <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                                                    <button class="btn btn-danger" type="submit">Refuser la demande</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>  <% } %>
                                <% for(int i=0;i<cor.length;i++){%>
                                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="val<%=cor[i].getId()%>" class="modal fade">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                                <h4 class="modal-title">VALIDATION</h4>
                                            </div>
                                            <div class="modal-body">                  <form action="validerdemande" method="get">
                                                    <input type="hidden" name="id" value="<%=cor[i].getId()%>">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                                                        <button class="btn btn-theme" type="submit">Valider demande</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>  <% } %>
                                </div>
                            </div>
                            <!-- /col-md-12 -->

                            <!-- /col-md-12 -->
                            <!-- row -->
                            <div class="row mt">
                                <div class="col-md-12">
                                    <div class="content-panel">

                                        <table style="width:80%" border="1" class="table table-striped table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th>Id<%=cor.length%></th>
                                                    <th>Nom</th>          
                                                    <th>DateDemande</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for(int i=0;i<cor.length;i++){%>
                                                                                                <tr>

                                            <td><%=cor[i].getMontant()%></td>
                                            <td><%=cor[i].getUsers().getNom()%></td>
                                            <td><%=cor[i].getDateDemande() %></td>
                                              <% if(cor[i].getState()==1){%>
                                                    <td  style='background-color: springgreen'><%=cor[i].getValidation() %></td>
                                                    <% } %> <% if(cor[i].getState()==-2){%>
                                                <td  style='background-color: #ffc4c4'><%=cor[i].getValidation() %></td>
                                                    <% } %>
                                             <td>
                                                <% if(cor[i].getState()==0){%>
                                                <a data-toggle="modal" href="#val<%=cor[i].getId()%>"> 
                                                    <button class="btn btn-primary btn-xs"><i class="fa fa-check">Valider</i></button></a>
                                                <a data-toggle="modal" href="#del<%=cor[i].getId()%>">  <button class="btn btn-danger btn-xs"><i class="fa fa-trash  ">Refuser</i></button>
                                                    <% } %>
                                            </td>
                                            </tr>
                                            <%
                                            } 
                                            %>
                                            </tbody>
                                        </table>

                                    </div>
                                    <!-- /content-panel -->
                                </div>
                                <!-- /col-md-12 -->
                            </div>
                            <!-- /row -->
                            </section>
                            </section>
                            <!-- /MAIN CONTENT -->
                            <!--main content end-->
                            <!--footer start-->
                            <%
                            
                            %>
                            <!--footer end-->
                            </section>
                            <!-- js placed at the end of the document so the pages load faster -->
                            <script src="../lib/jquery/jquery.min.js"></script>
                            <script src="../lib/bootstrap/js/bootstrap.min.js"></script>
                            <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
                            <script src="../lib/jquery.scrollTo.min.js"></script>
                            <script src="../lib/jquery.nicescroll.js" type="text/javascript"></script>
                            <!--common script for all pages-->
                            <script src="../lib/common-scripts.js"></script>
                            <!--script for this page-->

                            </body>

                            </html>
