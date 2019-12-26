<?php include 'include/header.php'; ?>

	<!-- Main -->
	<div id="main">
		<div id="job_posts">
		</div>
        <!-- Pagination -->
		<ul class="actions pagination">
			<li><a id="pagination_prev_button" class="disabled button large previous" onclick="prevPagePressed()">Previous Page</a></li>
			<li><a id="pagination_next_button" class="disabled button large next" onclick="nextPagePressed()">Next Page</a></li>
		</ul>
	</div>

<?php include 'include/sidebar_header.php'; ?>

    <section>
        <div id = "user_info">
        </div>
    </section>
    
    <section class="blurb">
        <h2>Reviews</h2>
        <div id = "user_reviews">
        </div>
        <ul class="actions">
                <li><a id="review_prev_button" class="disabled button medium previous" onclick="prevReviewPressed()">Previous Review</a></li>
            <li><a id="review_next_button" class="disabled button medium next" onclick="nextReviewPressed()">Next Review</a></li>

        </ul>
        <ul class = "actions">
                <li><a href='#' class='button medium'>Leave review</a></li>
        </ul>
    </section>

<?php include 'include/sidebar_footer.php'; ?>

	<script src="script/userProfile.js"></script>

<?php include 'include/footer.php'; ?> 