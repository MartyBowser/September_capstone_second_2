package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public class TransferDao {
    public Transfer insertTransfer(Transfer transfer);
    Transfer getTransferByUserId(int transferId);
}
