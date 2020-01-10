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
            
			<section id="users_info">
                <p><em><strong>Datele tuturor utilizatori</strong></em></p> 
                <div class="container">
                  <input class="form-control" id="search-input" type="text" placeholder="Cauta..">
                  <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                          <th><em><ins>Username</ins></em></th>
                          <th><em><ins>Email</ins></em></th>
                          <th><em><ins>Telefon</ins></em></th>
                          <th><em><ins>Descriere</ins></em></th>
                          <th><em><ins>Furnizor</ins></em></th>
                          <th><em><ins>Admin</ins></em></th>
                      </tr>
                    </thead>
                    <tbody id="users-table">
                    </tbody>
                  </table>

                </div>
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
                
                 <li><a id="search-update-button" class="button large fit">Modifica utilizator</a></li>
                
                <li><a id="delete-button" class="button large fit">Stergere utilizator</a></li>

            </ul>
        </section>
        
	</section>

<?php include 'include/sidebar_footer.php'; ?>
	
	<!-- Scripts used only in this page -->
    <script src="script/adminPanel.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
    $(document).ready(function(){
      $("#search-input").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#users-table tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
</script>

<?php include 'include/footer.php'; ?>