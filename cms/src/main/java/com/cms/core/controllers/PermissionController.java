package com.cms.core.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.core.models.Base;
import com.cms.core.models.Permission;
import com.cms.core.services.PermissionService;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@PutMapping
	public ResponseEntity<Base> save(@RequestBody Permission permission) {
		return ResponseEntity.ok(new Base(permissionService.save(permission)));
	}

	@PostMapping
	public ResponseEntity<Base> update(@RequestBody Permission permission) {
		return ResponseEntity.ok(new Base(permissionService.update(permission)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Base> delete(@PathVariable Integer id) {
		return ResponseEntity.ok(new Base(permissionService.deleteById(id)));
	}

	@DeleteMapping("/all")
	public ResponseEntity<Base> delete() {
		return ResponseEntity.ok(new Base(permissionService.deleteAll()));
	}

	@GetMapping
	public ResponseEntity<List<Permission>> findAll(SpringDataWebProperties.Pageable pageable) {
		return ResponseEntity.ok(permissionService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Permission> findById(@PathVariable int id) {
		return ResponseEntity.ok(permissionService.findById(id));
	}
}
