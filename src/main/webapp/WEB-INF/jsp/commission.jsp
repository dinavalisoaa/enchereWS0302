<!DOCTYPE html>
<html lang="en">
    <%@page import="model.*" %>
        <%@page import="java.util.ArrayList" %>

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

    
    </head>                        <% ArrayList<Commission> cor=(ArrayList<Commission>)request.getAttribute("commission");%>

    <body>
        <section id="container">
            
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
                    <h3><i class="fa fa-angle-right"></i>Commission</h3>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="content-panel">
                                <div class="showback">
                                    <a href="allcategorie">   <button type="button" class="btn btn-primary">Categorie</button></a>
                                    <a href="allcommissions">   <button type="button" class="btn btn-warning">Commission</button></a>

                                </div>
                            </div>
                            <!-- Modal -->
                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="newCat" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title">Mis a jour des commissions</h4>
                                        </div>
                                        <div class="modal-body">                  
                                            <form action="addCommission" method="get">

                                                <p>TAUX DE COMMISSION</p>
                                                <input type="text" name="taux"value="<%=new Commission().getCurrentId().getTaux()%>" placeholder="nom" class="form-control placeholder-no-fix">
                                                </div>
                                                <div class="modal-footer">
                                                    <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                                                    <button class="btn btn-theme" type="submit">Submit</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>        <!-- Modal -->


                            </div>
                        </div>
                        <!-- /col-md-12 -->

                        <!-- /col-md-12 -->
                        <!-- row -->
                        <div class="row mt">
                            <div class="col-md-12">
                                <div class="content-panel">
                                    <h2>Taux en cours:<%
                                            
                                        new Commission().getCurrentId().getTaux()%>%</h2>
                                        <table style="width:80%" border="1" class="table table-striped table-responsive-md">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>TAUX</th>          
                                                    <th>DATY</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% for(int i=0;i<cor.size();i++){%>
                                                <% if(cor.get(i).getId()==cor.get(i).getCurrentId().getId()){%>
                                                <tr style="background-color: antiquewhite">
                                                    <% } %>
                                                                                                        <th></th>
                                                                                                        <td></td>
                                            <td><%=cor.get(i).getTaux()%></td>
                                            <td><%=cor.get(i).getDaty()%></td>
                                            <td>  <a data-toggle="modal" href="#newCat"> 
                                        <button class="btn btn-primary"><i class="fa fa-plus">Add</i></button></a>
                                  </td>
                                            </tr>
                                            <%
                                            } 
                                            %>
                                            </tbody>
                                        </table>

                                </div>
                            </div>
                            <!-- /col-md-12 -->
                        </div>
                        <!-- /row -->
                </section>
            </section>
            <!-- /MAIN CONTENT -->
            <!--main content end-->
            <!--footer start-->
             <!-- js placed at the end of the document so the pages load faster -->
        <script src="../lib/jquery/jquery.min.js"></script>
        <script src="../lib/bootstrap/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript" src="../lib/jquery.dcjqaccordion.2.7.js"></script>
        <script src="../lib/jquery.scrollTo.min.js"></script>
        <script src="../lib/jquery.nicescroll.js" type="text/javascript"></script>
        <!--common script for all pages-->
        <script src="../lib/common-scripts.js"></script>
        <!--script for this page-->

    </body>

</html>
