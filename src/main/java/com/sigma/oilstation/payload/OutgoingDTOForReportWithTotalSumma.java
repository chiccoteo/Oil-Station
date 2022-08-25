package com.sigma.oilstation.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OutgoingDTOForReportWithTotalSumma {

    private double totalSumma;

    private List<OutgoingGetDTOForReport> outgoingGetDTOForReports;

}
