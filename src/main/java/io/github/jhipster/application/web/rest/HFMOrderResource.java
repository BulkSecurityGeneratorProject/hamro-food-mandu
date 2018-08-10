package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.HFMOrderService;
import io.github.jhipster.application.service.dto.HFMOrderDTO;
import io.github.jhipster.application.service.dto.TrayFoodDTO;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.HFMOrderDTO;
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
 * REST controller for managing HFMOrder.
 */
@RestController
@RequestMapping("/api")
public class HFMOrderResource {

    private final Logger log = LoggerFactory.getLogger(HFMOrderResource.class);

    private static final String ENTITY_NAME = "hFMOrder";

    private final HFMOrderService hFMOrderService;

    public HFMOrderResource(HFMOrderService hFMOrderService) {
        this.hFMOrderService = hFMOrderService;
    }

    /**
     * POST  /hfm-orders : Create a new hFMOrder.
     *
     * @param hFMOrderDTO the hFMOrderDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hFMOrderDTO, or with status 400 (Bad Request) if the hFMOrder has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hfm-orders")
    @Timed
    public ResponseEntity<HFMOrderDTO> createHFMOrder(@RequestBody HFMOrderDTO hFMOrderDTO) throws URISyntaxException {
        log.debug("REST request to save HFMOrder : {}", hFMOrderDTO);
        if (hFMOrderDTO.getId() != null) {
            throw new BadRequestAlertException("A new hFMOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HFMOrderDTO result = hFMOrderService.save(hFMOrderDTO);
        return ResponseEntity.created(new URI("/api/hfm-orders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hfm-orders : Updates an existing hFMOrder.
     *
     * @param hFMOrderDTO the hFMOrderDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hFMOrderDTO,
     * or with status 400 (Bad Request) if the hFMOrderDTO is not valid,
     * or with status 500 (Internal Server Error) if the hFMOrderDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hfm-orders")
    @Timed
    public ResponseEntity<HFMOrderDTO> updateHFMOrder(@RequestBody HFMOrderDTO hFMOrderDTO) throws URISyntaxException {
        log.debug("REST request to update HFMOrder : {}", hFMOrderDTO);
        if (hFMOrderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HFMOrderDTO result = hFMOrderService.save(hFMOrderDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hFMOrderDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hfm-orders : get all the hFMOrders.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hFMOrders in body
     */
    @GetMapping("/hfm-orders")
    @Timed
    public ResponseEntity<List<HFMOrderDTO>> getAllHFMOrders(Pageable pageable) {
        log.debug("REST request to get a page of HFMOrders");
        Page<HFMOrderDTO> page = hFMOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hfm-orders");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /hfm-orders/:id : get the "id" hFMOrder.
     *
     * @param id the id of the hFMOrderDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hFMOrderDTO, or with status 404 (Not Found)
     */
    @GetMapping("/hfm-orders/{id}")
    @Timed
    public ResponseEntity<HFMOrderDTO> getHFMOrder(@PathVariable Long id) {
        log.debug("REST request to get HFMOrder : {}", id);
        Optional<HFMOrderDTO> hFMOrderDTO = hFMOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hFMOrderDTO);
    }

    /**
     * DELETE  /hfm-orders/:id : delete the "id" hFMOrder.
     *
     * @param id the id of the hFMOrderDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hfm-orders/{id}")
    @Timed
    public ResponseEntity<Void> deleteHFMOrder(@PathVariable Long id) {
        log.debug("REST request to delete HFMOrder : {}", id);
        hFMOrderService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/hfm-orders/user/{userId}")
    @Timed
    public ResponseEntity<List<HFMOrderDTO>> getAllTrayFoodsByTrayId(@PathVariable Long userId) {
        log.debug("REST request to get a page of TrayFoods");
        Optional<List<HFMOrderDTO>> hfmOrderDTOList = hFMOrderService.findAllByUserId(userId);
        return ResponseUtil.wrapOrNotFound(hfmOrderDTOList);
    }

}
