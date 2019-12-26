<!DOCTYPE HTML>
<html>
	<head>
		<title>TransMarket</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/tooltip.css" />
		<link rel="shortcut icon" type="image/png" href="images/logo.png"/>
	</head>
	<body class="is-preload">
		<?php include 'include/modals.php'; ?>

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
                <header id="header">
                    <h1><a href="/">TransMarket</a></h1>
                    <nav class="links">
                        <ul>
                            <li><a href="/">Home</a></li>
                            <li id="user-profile-button"></li>
                            <li id="admin-panel"></li>
                        </ul>
                    </nav>
                    <nav class="main">
                        <ul>
                            <li class="search">
                                <a class="fa-search" href="#search">Search</a>
                                <form id="search" method="get" action="#">
                                    <input type="text" name="query" placeholder="Search" />
                                </form>
                            </li>
                            <li class="menu">
                                <a class="fa-bars" href="#menu">Menu</a>
                            </li>
                        </ul>
                    </nav>
                </header>

            <!-- Menu -->
                <section id="menu">

                    <!-- Search -->
                        <section>
                            <form class="search" method="get" action="#">
                                <input type="text" name="query" placeholder="Search" />
                            </form>
                        </section>

                    <!-- Links -->
                        <section>
                            <ul class="links">
                                <li>
                                    <a href="#">
                                        <h3>Home</h3>
                                        <p>View the latest offers</p>
                                    </a>
                                </li>
                                <li>
                                    <a href="#">
                                        <h3>News</h3>
                                        <p>Watch our newslatter and daily updates</p>
                                    </a>
                                </li>
                            </ul>
                        </section>

                    <!-- Actions -->
                    <section id="visitor-available-actions" style="display:none;">
                        <ul class="actions stacked">
                            <li><a id="login-button" class="button large fit">Autentificare</a></li>
                            <li><a id="register-button" class="button large fit">Inregistrare</a></li>
                        </ul>
                    </section>
                    <section id="user-available-actions" style="display:none;">
                        <ul class="actions stacked">
                            <li id="user-view-my-profile-button"></li>
                            <li><a class="button large fit" onclick="logMeOut()">Deautentificare</a></li>
                        </ul>
                    </section>
                    <section id="admin-available-actions" style="display:none;">
                        <ul class="actions stacked">
                            <li id="admin-platform-settings"></li>
                        </ul>
                    </section>
                </section>