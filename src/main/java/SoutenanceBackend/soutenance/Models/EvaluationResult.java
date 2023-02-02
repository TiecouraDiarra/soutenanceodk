package SoutenanceBackend.soutenance.Models;

import lombok.Data;

import java.util.Map;

@Data
public class EvaluationResult {

    private String studentId;
    private Map<Long, String> answers;
    private double score;
}
