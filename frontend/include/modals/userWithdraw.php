<div id="withdraw-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="withdraw-close">&times;</span>
            <h2>Extragere bani</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="withdraw-owner" placeholder="Numele detinatorului" />
                        <span class="tooltiptext" id="withdraw-owner-message">
                            Numele poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li class ="tooltip">
                        <input type="text" id="withdraw-card-number" placeholder="Numarul cardului" />
                        <span class="tooltiptext" id="withdraw-card-number-message">
                            Numarul poate contine doar cifre.
                        </span>

                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">   
                                <div class="tooltip">
                                    <input type="text" id="withdraw-CVV" placeholder="CVV" />
                                    <span class="tooltiptext" id="withdraw-CVV-message">
                                        CVV nu este valid.
                                    </span>
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="text" id="withdraw-amount" placeholder="Suma" />
                                    <span class="tooltiptext" id="withdraw-amount-message">
                                       Suma nu este valida.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="submit" id="submit-withdraw" value="Extragere" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>