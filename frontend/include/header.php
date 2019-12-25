<!DOCTYPE HTML>
<html>
	<head>
		<title>TransMarket</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="shortcut icon" type="image/png" href="images/logo.png"/>
		<link rel="shortcut icon" type="image/png" href="images/logo.png"/>
	</head>
	<body class="is-preload">

		<!-- Login Modal -->
		<div id="login-modal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<span class="close" id="login-close">&times;</span>
					<h2>Autentificare</h2>
				</div>
				<div class="modal-body">
					<div class="col-6 col-12-medium">					
						<ul class="alt">
							<li>
								<input type="text" id="login-username" placeholder="Nume de utilizator" />
							</li>
							<li>
								<input type="password" id="login-password" placeholder="Parola" />
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="submit" id="login-submit" value="Autentificare" class="button solid fit" />
									</div>
								</div>
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="submit" id="login-forgot" value="Ai uitat parola?" class="button fit" />	
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		
		</div>
		
		<!-- Register Modal -->
		<div id="register-modal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<span class="close" id="register-close">&times;</span>
					<h2>Inregistrare</h2>
				</div>
				<div class="modal-body">
					<div class="col-6 col-12-medium">					
						<ul class="alt">
							<li>
								<input type="text" id="register-username" placeholder="Nume de utilizator" />
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="password" id="register-password" placeholder="Parola" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="password" id="register-re-password" placeholder="Repeta parola" />
									</div>
								</div>
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="text" id="register-phone" placeholder="Numar de telefon" />
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="email" id="register-email" placeholder="Adresa de e-mail" />
									</div>
								</div>
							</li>
							<li>
								<textarea id="register-description" placeholder="Descriere personala"></textarea>
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<input type="checkbox" id="register-is-provider" />
										<label for="register-is-provider">Doresc sa fiu furnizor</label>
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="submit" id="register-submit" value="Inregistrare" class="button solid fit" />
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		
		</div>

		<!-- Forgot Modal -->
		<div id="forgot-modal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<span class="close" id="forgot-close">&times;</span>
					<h2>Am uitat parola</h2>
				</div>
				<div class="modal-body">
					<div class="col-6 col-12-medium">					
						<ul class="alt">
							<li>
								<input type="text" id="forgot-username" placeholder="Nume de utilizator" />
							</li>
							<li>
								<input type="email" id="forgot-email" placeholder="Adresa de e-mail" />
							</li>
							<li>
								<input type="text" id="forgot-phone" placeholder="Telefon" />
							</li>
							<li>
								<div class="row gtr-uniform">
									<div class="col-6 col-12-xsmall">
										<i>Completeaza doar unul din cele 3 campuri de mai sus.</i>
									</div>
									<div class="col-6 col-12-xsmall">
										<input type="submit" id="forgot-submit" value="Trimite" class="button fit" />	
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		
		</div>

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Header -->
                <header id="header">
                    <h1><a href="/">TransMarket</a></h1>
                    <nav class="links">
                        <ul>
                            <li><a href="/">Home</a></li>
                            <li><a href="#">News</a></li>
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
                        <section>
                            <ul class="actions stacked">
                                <li><a id="login-button" class="button large fit">Autentificare</a></li>
                                <li><a id="register-button" class="button large fit">Inregistrare</a></li>
                            </ul>
                        </section>

                </section>