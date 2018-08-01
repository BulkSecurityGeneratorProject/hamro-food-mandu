package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.OpeningHourService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.OpeningHourDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OpeningHour.
 */
@RestController
@RequestMapping("/api")
public class OpeningHourResource {

    private final Logger log = LoggerFactory.getLogger(OpeningHourResource.class);

    private static final String ENTITY_NAME = "openingHour";

    private final OpeningHourService openingHourService;

    public OpeningHourResource(OpeningHourService openingHourService) {
        this.openingHourService = openingHourService;
    }

    /**
     * POST  /opening-hours : Create a new openingHour.
     *
     * @param openingHourDTO the openingHourDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new openingHourDTO, or with status 400 (Bad Request) if the openingHour has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/opening-hours")
    @Timed
    public ResponseEntity<OpeningHourDTO> createOpeningHour(@RequestBody OpeningHourDTO openingHourDTO) throws URISyntaxException {
        log.debug("REST request to save OpeningHour : {}", openingHourDTO);
        if (openingHourDTO.getId() != null) {
            throw new BadRequestAlertException("A new openingHour cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpeningHourDTO result = openingHourService.save(openingHourDTO);
        return ResponseEntity.created(new URI("/api/opening-hours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /opening-hours : Updates an existing openingHour.
     *
     * @param openingHourDTO the openingHourDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated openingHourDTO,
     * or with status 400 (Bad Request) if the openingHourDTO is not valid,
     * or with status 500 (Internal Server Error) if the openingHourDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/opening-hours")
    @Timed
    public ResponseEntity<OpeningHourDTO> updateOpeningHour(@RequestBody OpeningHourDTO openingHourDTO) throws URISyntaxException {
        log.debug("REST request to update OpeningHour : {}", openingHourDTO);
        if (openingHourDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OpeningHourDTO result = openingHourService.save(openingHourDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, openingHourDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /opening-hours : get all the openingHours.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of openingHours in body
     */
    @GetMapping("/opening-hours")
    @Timed
    public ResponseEntity<List<OpeningHourDTO>> getAllOpeningHours(Pageable pageable) {
        log.debug("REST request to get a page of OpeningHours");
        Page<OpeningHourDTO> page = openingHourService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/opening-hours");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /opening-hours/:id : get the "id" openingHour.
     *
     * @param id the id of the openingHourDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the openingHourDTO, or with status 404 (Not Found)
     */
    @GetMapping("/opening-hours/{id}")
    @Timed
    public ResponseEntity<OpeningHourDTO> getOpeningHour(@PathVariable Long id) {
        log.debug("REST request to get OpeningHour : {}", id);
        Optional<OpeningHourDTO> openingHourDTO = openingHourService.findOne(id);
        return ResponseUtil.wrapOrNotFound(openingHourDTO);
    }

    /**
     * DELETE  /opening-hours/:id : delete the "id" openingHour.
     *
     * @param id the id of the openingHourDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/opening-hours/{id}")
    @Timed
    public ResponseEntity<Void> deleteOpeningHour(@PathVariable Long id) {
        log.debug("REST request to delete OpeningHour : {}", id);
        openingHourService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
