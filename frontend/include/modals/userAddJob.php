<div id="add-job-modal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <span class="close" id="add-job-close">&times;</span>
            <h2>Adaugare serviciu</h2>
        </div>
        <div class="modal-body">
            <div class="col-6 col-12-medium">					
                <ul class="alt">
                    <li>
                        <textarea id="add-job-description" placeholder="Descriere"></textarea>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            
                            <div class="col-6 col-12-xsmall">
                           
							<select name = "costs" id = "add-job-tag1">
								<option value="0">- Transport Type -</option>
								<option value="1">Carpooling</option>
								<option value="2">Ride-sharing</option>
								<option value="3">Freight transport</option>
							</select>
						    </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="col-6 col-12-xsmall">
                                <div class="tooltip"> 
                                    <input type="text" id="add-job-price" placeholder="Pret" />
                                    <span class="tooltiptext" id="add-job-price-message">
                                       Pretul are un format gresit.
                                    </span>
                                </div>
                                </div>
                            </div>
                        </div>
                    </li>
                     <li>
                        <textarea id="add-job-tags" placeholder="Tag-uri"></textarea>
                    </li>
                     <li>
                        <textarea id="add-job-route" placeholder="Ruta(opriri)"></textarea>
                    </li>
                    <li>
                        <textarea id="add-job-photo-links" placeholder="Link-uri fotografii serviciu"></textarea>
                    </li>
                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip"> 
                                    <h5>Data plecarii</h5>
                                   <input type="date" name="filter-calendar" id="add-job-departure-date" class="button icon fit fa-calendar" />
                                    
                                </div>
                            </div>
                            <div class="col-6 col-12-xsmall">
                                <div class="tooltip">
                                    <h5>Data sosirii</h5>
                                   <input type="date" name="filter-calendar" id="add-job-arrival-date" class="button icon fit fa-calendar" />
                                
                                </div>
                            </div>
                        </div>
                    </li>

                    <li>
                        <div class="row gtr-uniform">
                            <div class="col-6 col-12-xsmall">
                                <input type="submit-add-job" id="submit-add-job" value="Adaugare serviciu" class="button solid fit" />
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>