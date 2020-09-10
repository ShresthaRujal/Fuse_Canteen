package com.fuse.canteen.controller;

import com.fuse.canteen.dto.EmployeePositionDto;
import com.fuse.canteen.service.EmployeePositionService;
import com.fuse.canteen.utils.BaseController;
import com.fuse.canteen.utils.CustomMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/employeePosition")
@RequiredArgsConstructor
public class EmployeePositionController extends BaseController {

    private final CustomMessageSource customMessageSource;
    private final EmployeePositionService employeePositionService;
    private final String ModuleName = "EMPLOYEE POSITION";


    @PostMapping("create")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> create(@RequestBody @Valid EmployeePositionDto employeePositionDto) throws Exception {
        employeePositionService.create(employeePositionDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_SAVE,ModuleName)));
    }

    @PutMapping("edit")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> edit(@RequestBody @Valid EmployeePositionDto userDto) throws Exception {
        employeePositionService.edit(userDto);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_UPDATE,ModuleName)));
    }

    @GetMapping("get/{employee_id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> getById(@PathVariable("employee_id") Long employee_id) throws Exception {
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET,ModuleName),employeePositionService.getById(employee_id)));
    }

    @GetMapping("fetch")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> fetchAll(){
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_GET_ALL,ModuleName),employeePositionService.fetchAll()));
    }

    @DeleteMapping("delete/{employee_id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<?> delete(@PathVariable("employee_id") Long employee_id) throws Exception {
        employeePositionService.delete(employee_id);
        return ResponseEntity.ok(successResponse(customMessageSource.get(API_SUCCESS_DELETE,ModuleName)));
    }

}
