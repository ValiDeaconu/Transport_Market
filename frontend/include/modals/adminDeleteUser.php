<!-- Delete user by username Modal -->
<div id="delete-modal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="delete-close">&times;</span>
            <h2>Stergere utilizator</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="delete-user-username" placeholder="Numele utilizatorului" />
                        <span class="tooltiptext" id="del-username-message">
                            Numele de utilizator poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-4 col-12-xsmall">
                            </div>
                            <div class="col-4 col-12-xsmall">
                                <input type="submit" id="delete-submit" value="Stergere" class="button solid fit" />
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