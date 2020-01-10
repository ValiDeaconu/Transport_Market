<div id="deposit-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="deposit-close">&times;</span>
            <h2>Depunere bani</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li class="tooltip">
                        <input type="text" id="deposit-owner" placeholder="Numele detinatorului" />
                        <span class="tooltiptext" id="deposit-owner-message">
                            Numele poate contine doar caractere alfanumerice.
                        </span>
                    </li>
                    <li class ="tooltip">
                        <input type="text" id="deposit-card-number" placeholder="Numarul cardului" />
                        <span class="tooltiptext" id="deposit-card-number-message">
                            Numarul poate contine doar cifre.
                        </span>

                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">   
                                <div class="tooltip">
                                    <input type="text" id="deposit-CVV" placeholder="CVV" />
                                    <span class="tooltiptext" id="deposit-CVV-message">
                                        CVV nu este valid.
                                    </span>
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <input type="text" id="deposit-amount" placeholder="Suma" />
                                    <span class="tooltiptext" id="deposit-amount-message">
                                       Suma nu este valida.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="submit" id="submit-deposit" value="Depunere" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>