package app.controller;

import app.dto.Player;
import app.dto.Tile;
import app.dto.TileType;

public class TileController {
    private Tile[] board;
    private double[][] pading;

    public TileController() {
        board=new Tile[40];
        pading=new double[4][4];
        setBoard();
        setPading();
    }

    private void setBoard() {
        board[0]=new Tile(TileType.START,null,0);
        board[1]=new Tile(TileType.NORMAL_BROWN,"Aquapark Fala",40);
        board[2]=new Tile(TileType.COMMUNITY_CHEST,null,0);
        board[3]=new Tile(TileType.NORMAL_BROWN,"Moto Arena Łódź",60);
        board[4]=new Tile(TileType.INCOME_TAX,null,200);
        board[5]=new Tile(TileType.RAILROAD,"Łódź Fabryczna",120);
        board[6]=new Tile(TileType.NORMAL_BLUELIGHT,"Muzeum sztuki",80);
        board[7]=new Tile(TileType.CHANCE,null,0);
        board[8]=new Tile(TileType.NORMAL_BLUELIGHT,"Akademia muzyczna",100);
        board[9]=new Tile(TileType.NORMAL_BLUELIGHT,"Park Na Zdrowiu",100);
        board[10]=new Tile(TileType.JUST_VISITING,null,0);
        board[11]=new Tile(TileType.NORMAL_PINK,"Orientarium",120);
        board[12]=new Tile(TileType.ELECTRIC_COMPANY,null,120);
        board[13]=new Tile(TileType.NORMAL_PINK,"Zatoka Sportu",140);
        board[14]=new Tile(TileType.NORMAL_PINK,"Muzeum Kanału Dętka",140);
        board[15]=new Tile(TileType.RAILROAD,"Łódź Kaliska",120);
        board[16]=new Tile(TileType.NORMAL_ORANGE,"Uniwersytet Łódzki",160);
        board[17]=new Tile(TileType.COMMUNITY_CHEST,null,0);
        board[18]=new Tile(TileType.NORMAL_ORANGE,"Mandoria",180);
        board[19]=new Tile(TileType.NORMAL_ORANGE,"Off Piotrkowka",180);
        board[20]=new Tile(TileType.FREE_PARKING,null,0);
        board[21]=new Tile(TileType.NORMAL_RED,"Atlas Arena",210);
        board[22]=new Tile(TileType.CHANCE,null,0);
        board[23]=new Tile(TileType.NORMAL_RED,"Politechnika Łódzka",220);
        board[24]=new Tile(TileType.NORMAL_RED,"Stadion Miejski Widzewa Łódź",230);
        board[25]=new Tile(TileType.RAILROAD,"Łódź Widzew",120);
        board[26]=new Tile(TileType.NORMAL_YELLOW,"HollyŁódź",250);
        board[27]=new Tile(TileType.NORMAL_YELLOW,"UMED Łódź",260);
        board[28]=new Tile(TileType.WATER_WORKS,null,150);
        board[29]=new Tile(TileType.NORMAL_YELLOW,"Palmiarnia",280);
        board[30]=new Tile(TileType.JAIL,null,0);
        board[31]=new Tile(TileType.NORMAL_GREEN,"Stadion Miejski Łódzkiego Klubu Sportowego",300);
        board[32]=new Tile(TileType.NORMAL_GREEN,"Księży Młyn",300);
        board[33]=new Tile(TileType.COMMUNITY_CHEST,null,0);
        board[34]=new Tile(TileType.NORMAL_GREEN,"EC1 Łódź",320);
        board[35]=new Tile(TileType.RAILROAD,"Łódź Chojny",200);
        board[36]=new Tile(TileType.CHANCE,null,0);
        board[37]=new Tile(TileType.NORMAL_BLUEDARK,"Manufaktura",350);
        board[38]=new Tile(TileType.LUXURY_TAX,null,120);
        board[39]=new Tile(TileType.NORMAL_BLUEDARK,"Piotrkowska",400);
    }

    private void setPading() {
        // up
        pading[0][0]=290; // x
        pading[0][1]=60; // deltaX
        pading[0][2]=70; // y
        pading[0][3]=0; // deltaY

        // right
        pading[1][0]=900; // x
        pading[1][1]=0; // deltaX
        pading[1][2]=70; // y
        pading[1][3]=60; // deltaY

        // down
        pading[2][0]=900; // x
        pading[2][1]=-60; // deltaX
        pading[2][2]=680; // y
        pading[2][3]=0; // deltaY

        // left
        pading[3][0]=290; // x
        pading[3][1]=0; // deltaX
        pading[3][2]=680; // y
        pading[3][3]=-60; // deltaY
    }

    public Tile[] getBoard() { return board; }

    public double[][] getPading() { return pading; }

}
