<?php include 'include/header.php'; ?>

<?php include 'include/modals.php'; ?>

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
    <!-- Intro -->
        <section>
           <div id = "user_pic">
           </div>

            <div>
                <a id="edit-button" class="button fit">Editare profil</a>
                <a id="add-job-button" class="button fit">Adaugare serviciu</a>     
                <a id="deposit-button" class="button fit">Depunere bani</a>
                <a id="withdraw-button" class="button fit">Extragere bani</a>
                <a id="list-my-jobs" class="button fit" style="display: none;">Vizualizare oferte proprii</a>
                <a id="list-my-orders" class="button fit" style="display: block;">Vizualizare comenzi</a>
            </div>
           
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
        </section>



<?php include 'include/sidebar_footer.php'; ?>

	<script src="script/userOwnProfile.js"></script>
    <script src="script/modals/userEditProfile.js"></script>
    <script src="script/modals/userAddJob.js"></script>
    <script src="script/modals/userDeposit.js"></script>
    <script src="script/modals/userWithdraw.js"></script>

<?php include 'include/footer.php'; ?> 