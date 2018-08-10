package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TrayService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TrayDTO;
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
 * REST controller for managing Tray.
 */
@RestController
@RequestMapping("/api")
public class TrayResource {

    private final Logger log = LoggerFactory.getLogger(TrayResource.class);

    private static final String ENTITY_NAME = "tray";

    private final TrayService trayService;

    public TrayResource(TrayService trayService) {
        this.trayService = trayService;
    }

    /**
     * POST  /trays : Create a new tray.
     *
     * @param trayDTO the trayDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trayDTO, or with status 400 (Bad Request) if the tray has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trays")
    @Timed
    public ResponseEntity<TrayDTO> createTray(@RequestBody TrayDTO trayDTO) throws URISyntaxException {
        log.debug("REST request to save Tray : {}", trayDTO);
        if (trayDTO.getId() != null) {
            throw new BadRequestAlertException("A new tray cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrayDTO result = trayService.save(trayDTO);
        return ResponseEntity.created(new URI("/api/trays/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trays : Updates an existing tray.
     *
     * @param trayDTO the trayDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trayDTO,
     * or with status 400 (Bad Request) if the trayDTO is not valid,
     * or with status 500 (Internal Server Error) if the trayDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trays")
    @Timed
    public ResponseEntity<TrayDTO> updateTray(@RequestBody TrayDTO trayDTO) throws URISyntaxException {
        log.debug("REST request to update Tray : {}", trayDTO);
        if (trayDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrayDTO result = trayService.save(trayDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trayDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trays : get all the trays.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trays in body
     */
    @GetMapping("/trays")
    @Timed
    public ResponseEntity<List<TrayDTO>> getAllTrays(Pageable pageable) {
        log.debug("REST request to get a page of Trays");
        Page<TrayDTO> page = trayService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/trays");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /trays/:id : get the "id" tray.
     *
     * @param id the id of the trayDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trayDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trays/{id}")
    @Timed
    public ResponseEntity<TrayDTO> getTray(@PathVariable Long id) {
        log.debug("REST request to get Tray : {}", id);
        Optional<TrayDTO> trayDTO = trayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trayDTO);
    }

    /**
     * DELETE  /trays/:id : delete the "id" tray.
     *
     * @param id the id of the trayDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trays/{id}")
    @Timed
    public ResponseEntity<Void> deleteTray(@PathVariable Long id) {
        log.debug("REST request to delete Tray : {}", id);
        trayService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/trays/user/{userId}")
    @Timed
    public ResponseEntity<TrayDTO> getTrayByUserId(@PathVariable Long userId) {
        log.debug("REST request to get Tray by: {}", userId);
        Optional<TrayDTO> trayDTO = trayService.findOneByUserId(userId);
        return ResponseUtil.wrapOrNotFound(trayDTO);
    }
}
