<?php include 'include/header.php'; ?>

	<!-- Main -->
	<div id="main">
		<div id="main_posts">

		</div>

		<!-- Pagination -->
		<ul class="actions pagination">
			<li><a id="pagination_prev_button" class="disabled button large previous">Previous Page</a></li>
			<li><a id="pagination_next_button" class="disabled button large next">Next Page</a></li>
		</ul>
	</div>

<?php include 'include/sidebar_header.php'; ?>
	<!-- Intro -->
	<section id="intro">
		<a href="#" class="logo"><img src="images/logo.png" alt="" /></a>
		<header>
			<h2>TransMarket</h2>
		</header>
	</section>

	<!-- Filter panel -->
	<section>
		<div class="mini-posts">
			<div class="col-6 col-12-medium">
				<h4>Filter by</h4>
			
				<ul class="alt">
					<li>
						<input type="date" name="filter-calendar" id="filter-calendar" class="button icon fit fa-calendar" />
					</li>
					<li>
						<div class="row gtr-uniform">
							<div class="col-6 col-12-xsmall">
								<input type="number" name="filter-price-min" id="filter-price-min" min="0" max="30000" value="" placeholder="Min. price" />
							</div>
							<div class="col-6 col-12-xsmall">
								<input type="number" name="filter-price-max" id="filter-price-max" min="0" max="30000" value="" placeholder="Max. price" />
							</div>
						</div>
					</li>
					<li>
						<div class="col-12">
							<select name="filter-type" id="filter-type">
								<option value="0">-</option>
								<option value="1">Carpooling</option>
								<option value="2">Ride-sharing</option>
								<option value="3">Freight transport</option>
							</select>
						</div>
					</li>
					<li>
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="Filter" class="button fit" id="filter-submit" /></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</section>

<?php include 'include/sidebar_footer.php'; ?>

<script src="script/MainPage.js"></script>

<?php include 'include/footer.php'; ?>