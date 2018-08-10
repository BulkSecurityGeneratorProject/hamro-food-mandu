package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.TrayFoodService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.TrayFoodDTO;
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
 * REST controller for managing TrayFood.
 */
@RestController
@RequestMapping("/api")
public class TrayFoodResource {

    private final Logger log = LoggerFactory.getLogger(TrayFoodResource.class);

    private static final String ENTITY_NAME = "trayFood";

    private final TrayFoodService trayFoodService;

    public TrayFoodResource(TrayFoodService trayFoodService) {
        this.trayFoodService = trayFoodService;
    }

    /**
     * POST  /tray-foods : Create a new trayFood.
     *
     * @param trayFoodDTO the trayFoodDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trayFoodDTO, or with status 400 (Bad Request) if the trayFood has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tray-foods")
    @Timed
    public ResponseEntity<TrayFoodDTO> createTrayFood(@RequestBody TrayFoodDTO trayFoodDTO) throws URISyntaxException {
        log.debug("REST request to save TrayFood : {}", trayFoodDTO);
        if (trayFoodDTO.getId() != null) {
            throw new BadRequestAlertException("A new trayFood cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrayFoodDTO result = trayFoodService.save(trayFoodDTO);
        return ResponseEntity.created(new URI("/api/tray-foods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tray-foods : Updates an existing trayFood.
     *
     * @param trayFoodDTO the trayFoodDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trayFoodDTO,
     * or with status 400 (Bad Request) if the trayFoodDTO is not valid,
     * or with status 500 (Internal Server Error) if the trayFoodDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tray-foods")
    @Timed
    public ResponseEntity<TrayFoodDTO> updateTrayFood(@RequestBody TrayFoodDTO trayFoodDTO) throws URISyntaxException {
        log.debug("REST request to update TrayFood : {}", trayFoodDTO);
        if (trayFoodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrayFoodDTO result = trayFoodService.save(trayFoodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trayFoodDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tray-foods : get all the trayFoods.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of trayFoods in body
     */
    @GetMapping("/tray-foods")
    @Timed
    public ResponseEntity<List<TrayFoodDTO>> getAllTrayFoods(Pageable pageable) {
        log.debug("REST request to get a page of TrayFoods");
        Page<TrayFoodDTO> page = trayFoodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tray-foods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tray-foods/:id : get the "id" trayFood.
     *
     * @param id the id of the trayFoodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trayFoodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tray-foods/{id}")
    @Timed
    public ResponseEntity<TrayFoodDTO> getTrayFood(@PathVariable Long id) {
        log.debug("REST request to get TrayFood : {}", id);
        Optional<TrayFoodDTO> trayFoodDTO = trayFoodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trayFoodDTO);
    }

    /**
     * DELETE  /tray-foods/:id : delete the "id" trayFood.
     *
     * @param id the id of the trayFoodDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tray-foods/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrayFood(@PathVariable Long id) {
        log.debug("REST request to delete TrayFood : {}", id);
        trayFoodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/tray-foods/tray/{trayId}")
    @Timed
    public ResponseEntity<List<TrayFoodDTO>> getAllTrayFoodsByTrayId(@PathVariable Long trayId) {
        log.debug("REST request to get a page of TrayFoods");
        Optional<List<TrayFoodDTO>> trayFoodDTOList = trayFoodService.findAllByTrayId(trayId);
        return ResponseUtil.wrapOrNotFound(trayFoodDTOList);
    }
}
