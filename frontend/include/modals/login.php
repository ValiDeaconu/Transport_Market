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
                    <li class="tooltip">
                        <input type="text" id="login-username" placeholder="Nume de utilizator" />
                        <span class="tooltiptext" id="login-username-message">
                            Numele de utilizator poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li>
                        <input type="password" id="login-password" placeholder="Parola" />
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-4 col-12-xsmall">
                            </div>
                            <div class="col-4 col-12-xsmall">
                                <input type="submit" id="login-submit" value="Autentificare" class="button solid fit" />
                            </div>
                            <div class="col-4 col-12-xsmall">
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-4 col-12-xsmall">
                            </div>
                            <div class="col-4 col-12-xsmall">
                                <input type="submit" id="login-forgot" value="Ai uitat parola?" class="button fit" />	
                            </div>
                            <div class="col-4 col-12-xsmall">
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>