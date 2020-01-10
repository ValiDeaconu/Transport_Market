<div id="edit-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="edit-close">&times;</span>
            <h2>Editare profil</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="edit-username" placeholder="Nume de utilizator" />
                        <span class="tooltiptext" id="edit-username-message">
                            Numele poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="password" id="edit-password" placeholder="Parola" />
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="password" id="edit-re-password" placeholder="Repeta parola" />
                                    <span class="tooltiptext" id="edit-re-password-message">
                                        Parolele nu coincid.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip"> 
                                    <input type="text" id="edit-phone" placeholder="Numar de telefon" />
                                    <span class="tooltiptext" id="edit-phone-message">
                                        Numarul de telefon nu este valid.
                                    </span>
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="email" id="edit-email" placeholder="Adresa de e-mail" />
                                    <span class="tooltiptext" id="edit-email-message">
                                        Adresa de email nu este valida.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                     <li class="tooltip">
                        <input type="text" id="edit-profile-pic" placeholder="Link fotografie de profil" />
                        
                    </li>
                    <li>
                        <textarea id="edit-description" placeholder="Descriere personala"></textarea>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="submit-edit" id="submit-edit" value="Editare profil" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>