package com.iitms.cms.masters.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iitms.cms.masters.entity.ComplaintTypeEntity;
import com.iitms.cms.masters.service.ComplaintTypeService;

@Controller
//@RequestMapping("/types")
public class ComplaintTypeController {

	private static final Logger logger = LoggerFactory.getLogger(ComplaintTypeController.class);
	
	@Autowired
	private ComplaintTypeService complaintTypeService;
	
	@RequestMapping(value = "/get-complaints")
	public @ResponseBody List<ComplaintTypeEntity> getComplaintList(@RequestParam(required = false, defaultValue = "0") Integer deptId){
		logger.info("Get complaints : " + deptId + " - " + complaintTypeService);
		List<ComplaintTypeEntity> list = complaintTypeService.getComplaints(deptId);
		return list;
	}
	
	@RequestMapping(value = "/add-complaint", method = RequestMethod.POST)
	public String addComplaint(@ModelAttribute(value = "complaint") ComplaintTypeEntity complaint){
		logger.info("Add Complaint : " + complaint);
		complaintTypeService.addComplaint(complaint);
		return "redirect:/complaint-type";
	}
	
	@RequestMapping(value = "/update-complaint", method = RequestMethod.POST)
	public String updateComplaint(@ModelAttribute(value = "complaint") ComplaintTypeEntity complaint){
		logger.info("Update Complaint : " + complaint);
		complaintTypeService.updateComplaint(complaint);
		return "redirect:/complaint-type";
	}
	
	@RequestMapping(value = "/delete-complaint", method = RequestMethod.POST)
	public String deleteComplaint(@RequestParam Integer id){
		logger.info("Complaint : " + id);
		complaintTypeService.deleteComplaint(id);
		return "redirect:/complaint-type";
	}
	
	@RequestMapping(value = "/complaint-type")
	public String getComplaintTypes(Model model){
		model.addAttribute("complaint", new ComplaintTypeEntity());
		List<ComplaintTypeEntity> list = complaintTypeService.getComplaints(0);
		model.addAttribute("list", list);
		return "department-type";
	}
	
	
}
