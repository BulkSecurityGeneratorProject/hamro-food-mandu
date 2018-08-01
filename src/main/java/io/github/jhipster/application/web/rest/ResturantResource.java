package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.ResturantService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.ResturantDTO;
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
 * REST controller for managing Resturant.
 */
@RestController
@RequestMapping("/api")
public class ResturantResource {

    private final Logger log = LoggerFactory.getLogger(ResturantResource.class);

    private static final String ENTITY_NAME = "resturant";

    private final ResturantService resturantService;

    public ResturantResource(ResturantService resturantService) {
        this.resturantService = resturantService;
    }

    /**
     * POST  /resturants : Create a new resturant.
     *
     * @param resturantDTO the resturantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resturantDTO, or with status 400 (Bad Request) if the resturant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resturants")
    @Timed
    public ResponseEntity<ResturantDTO> createResturant(@RequestBody ResturantDTO resturantDTO) throws URISyntaxException {
        log.debug("REST request to save Resturant : {}", resturantDTO);
        if (resturantDTO.getId() != null) {
            throw new BadRequestAlertException("A new resturant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResturantDTO result = resturantService.save(resturantDTO);
        return ResponseEntity.created(new URI("/api/resturants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resturants : Updates an existing resturant.
     *
     * @param resturantDTO the resturantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resturantDTO,
     * or with status 400 (Bad Request) if the resturantDTO is not valid,
     * or with status 500 (Internal Server Error) if the resturantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resturants")
    @Timed
    public ResponseEntity<ResturantDTO> updateResturant(@RequestBody ResturantDTO resturantDTO) throws URISyntaxException {
        log.debug("REST request to update Resturant : {}", resturantDTO);
        if (resturantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResturantDTO result = resturantService.save(resturantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resturantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resturants : get all the resturants.
     *
     * @param pageable the pagination information
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of resturants in body
     */
    @GetMapping("/resturants")
    @Timed
    public ResponseEntity<List<ResturantDTO>> getAllResturants(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Resturants");
        Page<ResturantDTO> page;
        if (eagerload) {
            page = resturantService.findAllWithEagerRelationships(pageable);
        } else {
            page = resturantService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, String.format("/api/resturants?eagerload=%b", eagerload));
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /resturants/:id : get the "id" resturant.
     *
     * @param id the id of the resturantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resturantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resturants/{id}")
    @Timed
    public ResponseEntity<ResturantDTO> getResturant(@PathVariable Long id) {
        log.debug("REST request to get Resturant : {}", id);
        Optional<ResturantDTO> resturantDTO = resturantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resturantDTO);
    }

    /**
     * DELETE  /resturants/:id : delete the "id" resturant.
     *
     * @param id the id of the resturantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resturants/{id}")
    @Timed
    public ResponseEntity<Void> deleteResturant(@PathVariable Long id) {
        log.debug("REST request to delete Resturant : {}", id);
        resturantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
