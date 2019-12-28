<?php include 'include/header.php'; ?>

	<!-- Main -->
	<div id="main">
		<!-- Post -->
		<article class="post">
			<header>
				<div class="title">
					<h2><a href="#">Administrare date</a></h2>
					<p></p>
				</div>
				<div class="meta">
					<time class="published" datetime="2015-11-01">November 1, 2015</time>
					<a href="#" class="author"><span class="name">Jane Doe</span><img src="images/avatar.jpg" alt="" /></a>
				</div>
			</header>
			<span class="image featured"><img src="images/admin_work.jpg" alt="" /></span>
			
            <p><em>Alege utilizatorul pe care doresti sa il modifici</em></p> 
            
            <p><em>Alege furnizorul pe care doresti sa il modifici</em></p> 
			<footer>
				<ul class="stats">
					<li><a href="#">General</a></li>
					<li><a href="#" class="icon solid fa-heart">28</a></li>
					<li><a href="#" class="icon solid fa-comment">128</a></li>
				</ul>
			</footer>
		</article>
	</div>

<?php include 'include/sidebar_header.php'; ?>

	<!-- About -->
	<section class="blurb">
		<h2>About</h2>
		<p><em>Administratorul trebuie sa fie constient de puterea pe care o detine</em>.</p>
        
        <p>Gestiune utilizatori</p>
        
        <p>Gestiune furnizori</p>
	</section>

<?php include 'include/sidebar_footer.php'; ?>
	
	<!-- Scripts used only in this page -->
    <script src="script/adminPanel.js"></script>

<?php include 'include/footer.php'; ?>