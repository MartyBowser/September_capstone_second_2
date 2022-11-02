package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

public class TransferDao {

    void createTransfer(Transfer transfer);

    public Transfer insertTransfer(Transfer transfer);
    Transfer getTransferByUserId(int transferId);

    public Transfer updateBalances();
}
