<!-- Search user by username for update Modal -->
<div id="search-update-modal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="search-update-close">&times;</span>
            <h2>Modifica utilizator</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="search-update-username" placeholder="Intorduceti numele utilizatorului" />
                        <span class="tooltiptext" id="search-username-message">
                            Numele de utilizator poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <div id="load-data-update">
                     <li> 
                        <div class="row gtr-uniform">
                        <div class="col-6 col-12-xsmall">
                            <div class = "tooltip">
                                <input type="text" id="update-phone" placeholder="Numar telefon"/>
                                <span class="tooltiptext" id="update-phone-message">Numarul de telefon nu este valid.</span>
                            </div>
                        </div>
                            <div class="col-6 col-12-xsmall">
                            <div class = "tooltip">
                                <input type="text" id="update-email" placeholder="Email"/>
                                <span class="tooltiptext" id="update-email-message">Adresa de email nu este valida.</span>
                            </div>
                            </div>
                        </div>                           
                    </li>
                        <li>
                            <textarea id="update-description" placeholder="Descriere"></textarea>
                        </li>
                        <li>
                            <div class="row gtr-uniform">
                                <div class="col-6 col-12-xsmall">
                                <input type="checkbox" id="update-is-provider" >
                                <label for="update-is-provider">Functie de furnizor</label>
                                </div>
                                <div class="col-6 col-12-xsmall">
                                <input type="checkbox" id="update-is-admin" >
                                <label for="update-is-admin">Functie de administrator</label>
                                </div>
                            </div>
                        </li>
                    </div>
                            
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-4 col-12-xsmall">
                            </div>
                            <div class="col-4 col-12-xsmall">
                                <input type="submit" id="search-submit" value="Cauta utilizator" class="button solid fit" />
                                <input type="submit" id="update-submit" value="Modifica utilizator" class="button solid fit" />
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