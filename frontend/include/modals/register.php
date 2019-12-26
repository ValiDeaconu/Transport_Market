<div id="register-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="register-close">&times;</span>
            <h2>Inregistrare</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="register-username" placeholder="Nume de utilizator" />
                        <span class="tooltiptext" id="register-username-message">
                            Numele poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="password" id="register-password" placeholder="Parola" />
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="password" id="register-re-password" placeholder="Repeta parola" />
                                    <span class="tooltiptext" id="register-re-password-message">
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
                                    <input type="text" id="register-phone" placeholder="Numar de telefon" />
                                    <span class="tooltiptext" id="register-phone-message">
                                        Numarul de telefon nu este valid.
                                    </span>
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="email" id="register-email" placeholder="Adresa de e-mail" />
                                    <span class="tooltiptext" id="register-email-message">
                                        Adresa de email nu este valida.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <textarea id="register-description" placeholder="Descriere personala"></textarea>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="checkbox" id="register-is-provider" />
                                <label for="register-is-provider">Doresc sa fiu furnizor</label>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <input type="submit" id="register-submit" value="Inregistrare" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>