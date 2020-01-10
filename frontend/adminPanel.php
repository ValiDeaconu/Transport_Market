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
				    <div id = "admin_info">
                    </div>
				</div>
			</header>
			<span class="image featured"><img src="images/admin_work.jpg" alt="" /></span>
            
			<section>
                <p><em>Datele tuturor utilizatorilor</em></p> 
            </section>
            
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
        <section id="admin-actions">
            <ul class="actions stacked">
                <li><a id="add-button" class="button large fit">Adaugare utilizator</a></li>
                
                <li><a id="delete-button" class="button large fit">Stergere utilizator</a></li>

            </ul>
        </section>
        
	</section>

<?php include 'include/sidebar_footer.php'; ?>
	
	<!-- Scripts used only in this page -->
    <script src="script/adminPanel.js"></script>

<?php include 'include/footer.php'; ?>