<div id="add-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="add-close">&times;</span>
            <h2>Adaugare utilizator</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="add-username" placeholder="Nume de utilizator" />
                        <span class="tooltiptext" id="add-username-message">
                            Numele poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="password" id="add-password" placeholder="Parola" />
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="password" id="add-re-password" placeholder="Repeta parola" />
                                    <span class="tooltiptext" id="add-re-password-message">
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
                                    <input type="text" id="add-phone" placeholder="Numar de telefon" />
                                    <span class="tooltiptext" id="add-phone-message">
                                        Numarul de telefon nu este valid.
                                    </span>
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="email" id="add-email" placeholder="Adresa de e-mail" />
                                    <span class="tooltiptext" id="add-email-message">
                                        Adresa de email nu este valida.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <textarea id="add-description" placeholder="Descriere personala"></textarea>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="checkbox" id="add-is-provider" />
                                <label for="add-is-provider">Doriti ca acest utilizator sa fie furnizor?</label>
                            </div>
                                <div class="col-6 col-12-xsmall">
                                <input type="checkbox" id="add-is-admin" />
                                <label for="add-is-admin">Doriti ca acest utilizator sa fie admin?</label>
                            </div>
                            <div class="col-12 col-12-xsmall">
                                <input type="submit" id="add-submit" value="Adaugare utilizator" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

