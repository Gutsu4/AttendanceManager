package com.example.workManagement.repository;

import com.example.workManagement.model.Attendance;
import com.example.workManagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceManager {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Attendance> getAllAttendance(){
        String sql = "SELECT * FROM attendance;\n";
        List<Map<String,Object>> att_map = jdbcTemplate.queryForList(sql);
        List<Attendance> attendances = new ArrayList<>();
        for(Map<String,Object> row : att_map){
            Attendance attendance = mapToAttendance(row);
            attendances.add(attendance);
        }
        return attendances;
    }

    public List<Attendance> getAttendanceById(Integer user_id){

        String sql = "SELECT * from attendance where user_id = ?";
        List<Map<String,Object>> att_map = jdbcTemplate.queryForList(sql,user_id);
        List<Attendance> attendances = new ArrayList<>();

        for(Map<String,Object> obj : att_map){
            Attendance attendance = mapToAttendance(obj);
            attendances.add(attendance);
        }
        return attendances;
    }
    private Attendance mapToAttendance(Map<String, Object> row) {
        Attendance attendance = new Attendance();
        attendance.setId((Integer)row.get("id"));
        attendance.setUserId((Integer)row.get("user_id"));

        java.sql.Date sqlDate = (java.sql.Date) row.get("date");
        if (sqlDate != null) {
            attendance.setDate(sqlDate.toLocalDate());
        }

        java.sql.Time sqlTime; // java.sql.Timeを使用

        sqlTime = (java.sql.Time) row.get("start_time");
        if (sqlTime != null) {
            attendance.setStartTime(sqlTime.toLocalTime());
        }

        sqlTime = (java.sql.Time) row.get("end_time");
        if (sqlTime != null) {
            attendance.setEndTime(sqlTime.toLocalTime());
        }

        attendance.setBreakDuration((Integer)row.get("break_duration"));

        sqlTime = (java.sql.Time) row.get("total_work_time");
        if (sqlTime != null) {
            attendance.setTotalWorkTime(sqlTime.toLocalTime());
        }

        return attendance;
    }
}
