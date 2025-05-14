package com.example.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.hospital.model.Appointment;
import com.example.hospital.model.Appointments;
import com.example.hospital.repo.AppointmentRepository;
import com.example.hospital.repo.AppointmentsRepository;

import ch.qos.logback.core.model.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @GetMapping("/")
    public String home() {
        return "index"; // JSP or Thymeleaf homepage
    }

    @GetMapping("/appointment")
    public String appointmentForm() {
        return "appointment"; // Appointment form view
    }

    @PostMapping("/appointment")
    public ModelAndView submitAppointment(
            @RequestParam String patientName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String doctor,
            @RequestParam String appointmentTime
    ) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            Appointment appointment = new Appointment();
            appointment.setPatientName(patientName);
            appointment.setEmail(email);
            appointment.setPhone(phone);
            appointment.setDoctor(doctor);
            appointment.setAppointmentTime(LocalDateTime.parse(appointmentTime, formatter));

            appointmentRepo.save(appointment);

            ModelAndView mv = new ModelAndView("success"); // success.html or JSP
            mv.addObject("message", "Appointment booked successfully!");
            mv.addObject("patientName", patientName);
            mv.addObject("doctorName", doctor);
            mv.addObject("appointmentTime", appointmentTime);
            mv.addObject("appointmentId", appointment.getId()); // assuming appointment ID is auto-generated

            return mv;

        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("error"); // error.html or JSP
            mv.addObject("errorMessage", "There was an error processing your appointment: " + e.getMessage());
            return mv;
        }
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // contact.html or JSP
    }
    @GetMapping("/category")
    public String category() {
        return "category"; // contact.html or JSP
    }
    @GetMapping("/services")
    public String services() {
        return "services"; // contact.html or JSP
    }
    @GetMapping("/about")
    public String about() {
        return "about"; // contact.html or JSP
    }
    @GetMapping("/home")
    public String home1() {
        return "home1"; // contact.html or JSP
    }
    @GetMapping("/doctor1")
    public String appointmentdoc1() {
        return "appointmentdoc1"; // contact.html or JSP
    }
    @GetMapping("/doctor2")
    public String appointmentdoc2() {
        return "appointmentdoc2"; // contact.html or JSP
    }
    @GetMapping("/doctor3")
    public String appointmentdoc3() {
        return "appointmentdoc3"; // contact.html or JSP
    }
    @Autowired
    private AppointmentsRepository appointmentsRepository; // ✅ correct way

    @GetMapping("/appointments")
    public String showAppointmentForm() {
        return "appointments"; // JSP name
    }

    @PostMapping("/confirmPayment")
    public ModelAndView confirmPaymentAndSave(@RequestParam String doctorName,
                                              @RequestParam String patientName,
                                              @RequestParam String date,
                                              @RequestParam String time,
                                              @RequestParam String cardNumber,
                                              @RequestParam String cvv) {
        try {
            if (cardNumber.length() == 16 && cvv.length() == 3) {
                Appointments appt = new Appointments();
                appt.setDoctorName(doctorName);
                appt.setPatientName(patientName);
                appt.setAppointmentDate(LocalDate.parse(date));
                appt.setTime(time);
                appointmentsRepository.save(appt);

                ModelAndView mv = new ModelAndView("sucessd1");
                mv.addObject("message", "Appointment booked successfully for Dr. " + doctorName);
                mv.addObject("patientName", patientName);
                mv.addObject("appointmentDate", date);
                mv.addObject("appointmentTime", time);
                return mv;
            } else {
                throw new Exception("Invalid payment details");
            }
        } catch (Exception e) {
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("errorMessage", "Payment failed or error booking appointment: " + e.getMessage());
            return mv;
        }
    }

}
