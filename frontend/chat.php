<?php include 'include/header.php'; ?>

	<!-- Main -->
	<div id="main">
		<!-- Post -->
		<article class="post">
			<div id="messages-block">
			</div>

			<div class="row gtr-uniform">
				<div class="col-10 col-9-medium col-8-small col-8-xsmall">
					<input type="text" id="message-input" placeholder="Aa.." />
				</div>
				<div class="col-2 col-3-medium col-4-small col-4-xsmall">
					<input type="submit" value="Send" class="button fit" id="message-submit" />
				</div>
			</div>
		</article>
	</div>

<?php include 'include/sidebar_header.php'; ?>

	<!-- About -->
	<section id="warning-stranger" style="display:none;">
		<i>Note: You're talking with a stranger</i>
	</section>

	<section>
		<div class="chat-history" id="chat-list">
		</div>
	</section>

<?php include 'include/sidebar_footer.php'; ?>
	
	<!-- Scripts used only in this page -->
	<script src="script/ChatSystem.js"></script>

<?php include 'include/footer.php'; ?>