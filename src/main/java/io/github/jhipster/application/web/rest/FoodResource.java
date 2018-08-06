package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.FoodService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.FoodDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Food.
 */
@RestController
@RequestMapping("/api")
public class FoodResource {

    private final Logger log = LoggerFactory.getLogger(FoodResource.class);

    private static final String ENTITY_NAME = "food";

    private final FoodService foodService;

    public FoodResource(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * POST  /foods : Create a new food.
     *
     * @param foodDTO the foodDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new foodDTO, or with status 400 (Bad Request) if the food has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foods")
    @Timed
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        log.debug("REST request to save Food : {}", foodDTO);
        if (foodDTO.getId() != null) {
            throw new BadRequestAlertException("A new food cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FoodDTO result = foodService.save(foodDTO);
        return ResponseEntity.created(new URI("/api/foods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /foods : Updates an existing food.
     *
     * @param foodDTO the foodDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated foodDTO,
     * or with status 400 (Bad Request) if the foodDTO is not valid,
     * or with status 500 (Internal Server Error) if the foodDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foods")
    @Timed
    public ResponseEntity<FoodDTO> updateFood(@RequestBody FoodDTO foodDTO) throws URISyntaxException {
        log.debug("REST request to update Food : {}", foodDTO);
        if (foodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FoodDTO result = foodService.save(foodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, foodDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foods : get all the foods.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of foods in body
     */
//    @PreAuthorize("hasRole('ROLE_ANONYMOUS') or hasRole('ROLE_ADMIN')")
    @GetMapping("/foods")
    @Timed
    public ResponseEntity<List<FoodDTO>> getAllFoods(Pageable pageable) {
        log.debug("REST request to get a page of Foods");
        Page<FoodDTO> page = foodService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/foods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /foods/:id : get the "id" food.
     *
     * @param id the id of the foodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the foodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/foods/{id}")
    @Timed
    public ResponseEntity<FoodDTO> getFood(@PathVariable Long id) {
        log.debug("REST request to get Food : {}", id);
        Optional<FoodDTO> foodDTO = foodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(foodDTO);
    }

    /**
     * DELETE  /foods/:id : delete the "id" food.
     *
     * @param id the id of the foodDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foods/{id}")
    @Timed
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        log.debug("REST request to delete Food : {}", id);
        foodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
