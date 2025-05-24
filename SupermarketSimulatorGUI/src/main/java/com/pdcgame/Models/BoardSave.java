/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdcgame.Models;

import com.pdcgame.Enums.BoardCell;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



/**
 *
 * @author sujal
 */
@Entity
@Table(name = "board_save")
public class BoardSave {
    @Id
    private String coordinateID;
    
    private BoardCell boardCell;
             
    public BoardSave() {} // default constructor
    
    public BoardSave(String coordinateID, BoardCell cell) {
        this.coordinateID = coordinateID;
        this.boardCell = cell;
    }
    
    public String getCoordinateID() {
        return this.coordinateID;
    }

    public BoardCell getBoardCell() {
        return boardCell;
    }

    public void setBoardCell(BoardCell boardCell) {
        this.boardCell = boardCell;
    }
    
    public void setCoordinateID(String coordinate) {
        this.coordinateID = coordinate;
    }
}
