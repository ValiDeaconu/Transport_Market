<?php include 'include/header.php'; ?>

	<!-- Main -->
	<div id="main">
		<div id="main_posts">

		</div>

		<!-- Pagination -->
		<ul class="actions pagination">
			<li><a id="pagination_prev_button" class="disabled button large previous" onclick="prevPagePressed()">Previous Page</a></li>
			<li><a id="pagination_next_button" class="disabled button large next" onclick="nextPagePressed()">Next Page</a></li>
		</ul>
	</div>

<?php include 'include/sidebar_header.php'; ?>

	<!-- Mini Posts -->
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
								<option value="0">- Transport Type -</option>
								<option value="1">Carpooling</option>
								<option value="2">Ride-sharing</option>
								<option value="3">Freight transport</option>
							</select>
						</div>
					</li>
					<li>
						<div class="col-12">
							<ul class="actions">
								<li><input type="submit" value="Filter" class="button fit" onclick="filterResults()" /></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</section>

	<!-- About -->
	<section class="blurb">
		<h2>About</h2>
		<p>Mauris neque quam, fermentum ut nisl vitae, convallis maximus nisl. Sed mattis nunc id lorem euismod amet placerat. Vivamus porttitor magna enim, ac accumsan tortor cursus at phasellus sed ultricies.</p>
		<ul class="actions">
			<li><a href="#" class="button">Learn More</a></li>
		</ul>
	</section>

<?php include 'include/sidebar_footer.php'; ?>

<script src="script/mainpage.js"></script>

<?php include 'include/footer.php'; ?>