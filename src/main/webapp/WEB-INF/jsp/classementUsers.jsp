<!DOCTYPE html>
<html lang="en">
    <%@page import="model.*" %>
    <%@page import="java.util.*" %>

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
    </head>
    <% ArrayList<Users>cor=(ArrayList<Users>)request.getAttribute("activiteUsers");%>
    <%// ArrayList<Users>ccat=(ArrayList<Users>)request.getAttribute("rentableUsers");%>
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
            < <section id="main-content">
                <section class="wrapper site-min-height">

                    <h3><i class="fa fa-angle-right"></i>Tableau statistiques<i class="fa fa-angle-right"></i>Utilisateurs</h3>
                    <div class="showback">
                                            <a href="classementCategorie">   <button type="button" class="btn btn-info">Classement des Categories</button></a>
                        <a href="classementUsers">   <button type="button" class="btn btn-facebook">Classement des Utilisateur</button></a>
   
                    </div>
                    <!-- page start-->
                    <div id="morris">
                        <div class="row mt">
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i>Interactivite des Utilisateurs</h4>
                                    <div class="card-body">
                                        <canvas id="barChart"></canvas>
                                    </div>
                                </div> 
                            </div>
                            <div class="col-lg-6">
                                <div class="content-panel">
                                    <h4><i class="fa fa-angle-right"></i>Utilisateur rentable</h4>
                                    <div class="panel-body">
                                        <%=request.getAttribute("rentableUsers")%>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- page end-->
                </section>
            </section>
            <!--footer end-->
        </section>
        <script src="../js/js/vendor.bundle.base.js"></script>
        <script src="../js/js.chart.js/Chart.min.js"></script>
        <!-- End plugin js for this page -->
        <!-- inject:js -->
        <script src="../js/off-canvas.js"></script>
        <script src="../js/hoverable-collapse.js"></script>
        <script src="../js/template.js"></script>
        <script src="../js/settings.js"></script>
        <script src="../js/todolist.js"></script>
        <!-- endinject -->
        <!-- Custom js for this page-->
        <!--<script src="../js/chart.js"></script>-->
        <!-- End custom js for this page-->
        <script src="../lib/chartjs-conf.js"></script>
    </body>
    <script>

        /* ChartJS
         * -------
         * Data and config for chartjs
         */
        'use strict';
        var data = {
        labels: [<% for(int i=0;i<cor.size();i++){ %>
        "<%=cor.get(i).getNom() %>",
        <% } %>],
                datasets: [{
                label: 'Activite',
                        data: [
        <% for(int i=0;i<cor.size();i++){ %>
        <%=cor.get(i).getNbEnchereFait()%>,
        <%} %>
                        ],
                        data: [10, 19, 3, 5, 2, 3],
                        backgroundColor: [
        <% for(int i=0;i<cor.size();i++){ %>
                        'rgba(54, 162, 235, 0.2)',
        <%} %>
                        ],
                        borderColor: [
        <% for(int i=0;i<cor.size();i++){ %>
                        'rgba(54, 162, 235, 0.2)',
        <%} %>

                        ],
                        borderWidth: 1,
                        fill: false
                }]
        };
        var multiLineData = {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
                datasets: [{
                label: 'Dataset 1',
                        data: [12, 19, 3, 5, 2, 3],
                        borderColor: [
                                '#587ce4'
                        ],
                        borderWidth: 2,
                        fill: false
                },
                {
                label: 'Dataset 2',
                        data: [5, 23, 7, 12, 42, 23],
                        borderColor: [
                                '#ede190'
                        ],
                        borderWidth: 2,
                        fill: false
                },
                {
                label: 'Dataset 3',
                        data: [15, 10, 21, 32, 12, 33],
                        borderColor: [
                                '#f44252'
                        ],
                        borderWidth: 2,
                        fill: false
                }
                ]
        };
        var options = {
        scales: {
        yAxes: [{
        ticks: {
        beginAtZero: true
        }
        }]
        },
                legend: {
                display: false
                },
                elements: {
                point: {
                radius: 0
                }
                }

        };
        var doughnutPieData = {
        datasets: [{
        data: [
        <% for(int i=0;i<cor.size();i++){ %>
        <%=cor.get(i).getNbEnchereFait()%>,
        <%} %>
        ],
                backgroundColor: [

        <% for(int i=0;i<cor.size();i++){ %>
        <% if(i%2==0){ %>
                'rgba(255, 99, 132, 0.5)',
        <%  %>
                'rgba(75, 192, 192, 0.5)',
        <% } %>
        <%} %>
                ],
        }],
                // These labels appear in the legend and in the tooltips when hovering different arcs
                labels: [
        <% for(int i=0;i<cor.size();i++){ %>
                "<%=cor.get(i).getNom()%>",
        <% } %>
                ]
        };
        var doughnutPieOptions = {
        responsive: true,
                animation: {
                animateScale: true,
                        animateRotate: true
                }
        };
        var areaData = {
        labels: [<% for(int i=0;i<cor.size();i++){ %>
        "<%=cor.get(i).getNom() %>",
        <% } %>],
                datasets: [{
                label: '# of Votes',
                        data: [
        <% for(int i=0;i<cor.size();i++){ %>
        <%=cor.get(i).getNbEnchereFait()%>,
        <%} %>
                        ],
                        backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)',
                                'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                                'rgba(255,99,132,1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)',
                                'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1,
                        fill: true, // 3: no fill
                }]
        };
        var areaOptions = {
        plugins: {
        filler: {
        propagate: true
        }
        }
        }

        var multiAreaData = {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                datasets: [{
                label: 'Facebook',
                        data: [8, 11, 13, 15, 12, 13, 16, 15, 13, 19, 11, 14],
                        borderColor: ['rgba(255, 99, 132, 0.5)'],
                        backgroundColor: ['rgba(255, 99, 132, 0.5)'],
                        borderWidth: 1,
                        fill: true
                },
                {
                label: 'Twitter',
                        data: [7, 17, 12, 16, 14, 18, 16, 12, 15, 11, 13, 9],
                        borderColor: ['rgba(54, 162, 235, 0.5)'],
                        backgroundColor: ['rgba(54, 162, 235, 0.5)'],
                        borderWidth: 1,
                        fill: true
                },
                {
                label: 'Linkedin',
                        data: [6, 14, 16, 20, 12, 18, 15, 12, 17, 19, 15, 11],
                        borderColor: ['rgba(255, 206, 86, 0.5)'],
                        backgroundColor: ['rgba(255, 206, 86, 0.5)'],
                        borderWidth: 1,
                        fill: true
                }
                ]
        };
        var multiAreaOptions = {
        plugins: {
        filler: {
        propagate: true
        }
        },
                elements: {
                point: {
                radius: 0
                }
                },
                scales: {
                xAxes: [{
                gridLines: {
                display: false
                }
                }],
                        yAxes: [{
                        gridLines: {
                        display: false
                        }
                        }]
                }
        }

        var scatterChartData = {
        datasets: [{
        label: 'First Dataset',
                data: [{
                x: - 10,
                        y: 0
                },
                {
                x: 0,
                        y: 3
                },
                {
                x: - 25,
                        y: 5
                },
                {
                x: 40,
                        y: 5
                }
                ],
                backgroundColor: [
                        'rgba(255, 99, 132, 0.2)'
                ],
                borderColor: [
                        'rgba(255,99,132,1)'
                ],
                borderWidth: 1
        },
        {
        label: 'Second Dataset',
                data: [{
                x: 10,
                        y: 5
                },
                {
                x: 20,
                        y: - 30
                },
                {
                x: - 25,
                        y: 15
                },
                {
                x: - 10,
                        y: 5
                }
                ],
                backgroundColor: [
                        'rgba(54, 162, 235, 0.2)',
                ],
                borderColor: [
                        'rgba(54, 162, 235, 1)',
                ],
                borderWidth: 1
        }
        ]
        }

        var scatterChartOptions = {
        scales: {
        xAxes: [{
        type: 'linear',
                position: 'bottom'
        }]
        }
        }
        // Get context with jQuery - using jQuery's .get() method.
        if ($("#barChart").length) {
        var barChartCanvas = $("#barChart").get(0).getContext("2d");
        // This will get the first returned node in the jQuery collection.
        var barChart = new Chart(barChartCanvas, {
        type: 'bar',
                data: data,
                options: options
        });
        }

        if ($("#lineChart").length) {
        var lineChartCanvas = $("#lineChart").get(0).getContext("2d");
        var lineChart = new Chart(lineChartCanvas, {
        type: 'line',
                data: data,
                options: options
        });
        }

        if ($("#linechart-multi").length) {
        var multiLineCanvas = $("#linechart-multi").get(0).getContext("2d");
        var lineChart = new Chart(multiLineCanvas, {
        type: 'line',
                data: multiLineData,
                options: options
        });
        }

        if ($("#areachart-multi").length) {
        var multiAreaCanvas = $("#areachart-multi").get(0).getContext("2d");
        var multiAreaChart = new Chart(multiAreaCanvas, {
        type: 'line',
                data: multiAreaData,
                options: multiAreaOptions
        });
        }

        if ($("#doughnutChart").length) {
        var doughnutChartCanvas = $("#doughnutChart").get(0).getContext("2d");
        var doughnutChart = new Chart(doughnutChartCanvas, {
        type: 'doughnut',
                data: doughnutPieData,
                options: doughnutPieOptions
        });
        }

        if ($("#pieChart").length) {
        var pieChartCanvas = $("#pieChart").get(0).getContext("2d");
        var pieChart = new Chart(pieChartCanvas, {
        type: 'pie',
                data: doughnutPieData,
                options: doughnutPieOptions
        });
        }

        if ($("#areaChart").length) {
        var areaChartCanvas = $("#areaChart").get(0).getContext("2d");
        var areaChart = new Chart(areaChartCanvas, {
        type: 'line',
                data: areaData,
                options: areaOptions
        });
        }

        if ($("#scatterChart").length) {
        var scatterChartCanvas = $("#scatterChart").get(0).getContext("2d");
        var scatterChart = new Chart(scatterChartCanvas, {
        type: 'scatter',
                data: scatterChartData,
                options: scatterChartOptions
        });
        }

        if ($("#browserTrafficChart").length) {
        var doughnutChartCanvas = $("#browserTrafficChart").get(0).getContext("2d");
        var doughnutChart = new Chart(doughnutChartCanvas, {
        type: 'doughnut',
                data: browserTrafficData,
                options: doughnutPieOptions
        });
        }



    </script>
</html>
