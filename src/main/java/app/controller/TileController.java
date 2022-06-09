package app.controller;

import app.dto.Player;
import app.dto.Tile;
import app.dto.TileType;
import javafx.scene.Group;

public class TileController {
    public static final int TILES_COUNTER = 40;
    private static final Tile[] board = new Tile[TILES_COUNTER];
    private static final Tile[] railroadsBoard = new Tile[4];
    private final double[][] padding;
    private final Group tiles;
    //class constructor which sets Group of tiles, board and padding
    public TileController(Group tiles) {
        this.tiles = tiles;
        padding = new double[4][4];
        setBoard();
        setPadding();
    }
    //set every tile
    private void setBoard() {
        board[0] = new Tile((Group) tiles.getChildren().get(0), TileType.START, null, 0, 0, 0, 0, 0);
        board[1] = new Tile((Group) tiles.getChildren().get(1), TileType.NORMAL_BROWN, "Aquapark Fala", 40, 10, 20, 100, 100);
        board[2] = new Tile((Group) tiles.getChildren().get(2), TileType.COMMUNITY_CHEST, null, 0, 0, 0, 0, 0);
        board[3] = new Tile((Group) tiles.getChildren().get(3), TileType.NORMAL_BROWN, "Moto Arena Łódź", 60, 15, 25, 110, 110);
        board[4] = new Tile((Group) tiles.getChildren().get(4), TileType.INCOME_TAX, null, 0, 200, 0, 0, 0);
        railroadsBoard[0] = board[5] = new Tile((Group) tiles.getChildren().get(5), "Łódź Fabryczna", 120, 25, 50, 100, 200);
        board[6] = new Tile((Group) tiles.getChildren().get(6), TileType.NORMAL_BLUELIGHT, "Muzeum sztuki", 80, 20, 30, 120, 120);
        board[7] = new Tile((Group) tiles.getChildren().get(7), TileType.CHANCE, null, 0, 0, 0, 0, 0);
        board[8] = new Tile((Group) tiles.getChildren().get(8), TileType.NORMAL_BLUELIGHT, "Akademia muzyczna", 100, 25, 35, 130, 130);
        board[9] = new Tile((Group) tiles.getChildren().get(9), TileType.NORMAL_BLUELIGHT, "Park Na Zdrowiu", 100, 30, 40, 140, 140);
        board[10] = new Tile((Group) tiles.getChildren().get(10), TileType.JUST_VISITING, null, 0, 0, 0, 0, 0);
        board[11] = new Tile((Group) tiles.getChildren().get(11), TileType.NORMAL_PINK, "Orientarium", 120, 35, 45, 150, 150);
        board[12] = new Tile((Group) tiles.getChildren().get(12), TileType.ELECTRIC_COMPANY, null, 0, 120, 0, 0, 0);
        board[13] = new Tile((Group) tiles.getChildren().get(13), TileType.NORMAL_PINK, "Zatoka Sportu", 140, 40, 50, 160, 160);
        board[14] = new Tile((Group) tiles.getChildren().get(14), TileType.NORMAL_PINK, "Muzeum Kanału Dętka", 140, 45, 55, 170, 170);
        railroadsBoard[1] = board[15] = new Tile((Group) tiles.getChildren().get(15), "Łódź Kaliska", 120, 25, 50, 100, 200);
        board[16] = new Tile((Group) tiles.getChildren().get(16), TileType.NORMAL_ORANGE, "Uniwersytet Łódzki", 160, 50, 60, 180, 180);
        board[17] = new Tile((Group) tiles.getChildren().get(17), TileType.COMMUNITY_CHEST, null, 0, 0, 0, 0, 0);
        board[18] = new Tile((Group) tiles.getChildren().get(18), TileType.NORMAL_ORANGE, "Mandoria", 180, 55, 65, 190, 190);
        board[19] = new Tile((Group) tiles.getChildren().get(19), TileType.NORMAL_ORANGE, "Off Piotrkowka", 180, 60, 70, 200, 200);
        board[20] = new Tile((Group) tiles.getChildren().get(20), TileType.FREE_PARKING, null, 0, 0, 0, 0, 0);
        board[21] = new Tile((Group) tiles.getChildren().get(21), TileType.NORMAL_RED, "Atlas Arena", 210, 65, 75, 210, 210);
        board[22] = new Tile((Group) tiles.getChildren().get(22), TileType.CHANCE, null, 0, 0, 0, 0, 0);
        board[23] = new Tile((Group) tiles.getChildren().get(23), TileType.NORMAL_RED, "Politechnika Łódzka", 220, 70, 80, 220, 220);
        board[24] = new Tile((Group) tiles.getChildren().get(24), TileType.NORMAL_RED, "Stadion Miejski Widzewa Łódź", 230, 75, 85, 230, 230);
        railroadsBoard[2] = board[25] = new Tile((Group) tiles.getChildren().get(25), "Łódź Widzew", 120, 25, 50, 100, 200);
        board[26] = new Tile((Group) tiles.getChildren().get(26), TileType.NORMAL_YELLOW, "HollyŁódź", 250, 80, 90, 240, 250);
        board[27] = new Tile((Group) tiles.getChildren().get(27), TileType.NORMAL_YELLOW, "UMED Łódź", 260, 85, 95, 250, 250);
        board[28] = new Tile((Group) tiles.getChildren().get(28), TileType.WATER_WORKS, null, 0, 150, 0, 0, 0);
        board[29] = new Tile((Group) tiles.getChildren().get(29), TileType.NORMAL_YELLOW, "Palmiarnia", 280, 90, 100, 260, 260);
        board[30] = new Tile((Group) tiles.getChildren().get(30), TileType.JAIL, null, 0, 0, 0, 0, 0);
        board[31] = new Tile((Group) tiles.getChildren().get(31), TileType.NORMAL_GREEN, "Stadion Miejski Łódzkiego Klubu Sportowego", 300, 95, 105, 270, 270);
        board[32] = new Tile((Group) tiles.getChildren().get(32), TileType.NORMAL_GREEN, "Księży Młyn", 300, 100, 110, 280, 280);
        board[33] = new Tile((Group) tiles.getChildren().get(33), TileType.COMMUNITY_CHEST, null, 0, 0, 0, 0, 0);
        board[34] = new Tile((Group) tiles.getChildren().get(34), TileType.NORMAL_GREEN, "EC1 Łódź", 320, 110, 120, 300, 300);
        railroadsBoard[3] = board[35] = new Tile((Group) tiles.getChildren().get(35), "Łódź Chojny", 200, 25, 50, 100, 200);
        board[36] = new Tile((Group) tiles.getChildren().get(36), TileType.CHANCE, null, 0, 0, 0, 0, 0);
        board[37] = new Tile((Group) tiles.getChildren().get(37), TileType.NORMAL_BLUEDARK, "Manufaktura", 350, 120, 130, 320, 320);
        board[38] = new Tile((Group) tiles.getChildren().get(38), TileType.LUXURY_TAX, null, 0, 200, 0, 0, 0);
        board[39] = new Tile((Group) tiles.getChildren().get(39), TileType.NORMAL_BLUEDARK, "Piotrkowska", 400, 130, 140, 330, 330);
    }

    //sets padding in every direction
    private void setPadding() {
        // up
        padding[0][0] = 290; // x
        padding[0][1] = 60; // deltaX
        padding[0][2] = 70; // y
        padding[0][3] = 0; // deltaY

        // right
        padding[1][0] = 900; // x
        padding[1][1] = 0; // deltaX
        padding[1][2] = 70; // y
        padding[1][3] = 60; // deltaY

        // down
        padding[2][0] = 900; // x
        padding[2][1] = -60; // deltaX
        padding[2][2] = 680; // y
        padding[2][3] = 0; // deltaY

        // left
        padding[3][0] = 290; // x
        padding[3][1] = 0; // deltaX
        padding[3][2] = 680; // y
        padding[3][3] = -60; // deltaY
    }
    //getter of board
    public static Tile[] getBoard() {
        return board;
    }

    public static Tile[] getRailroadsBoard() {
        return railroadsBoard;
    }
    //getter of padding
    public double[][] getPadding() {
        return padding;
    }
    //check if player has every colour of tile
    public static int howManySpecificTypeTilesPlayerHas(TileType type, Player owner) {
        Tile[] tiles = new Tile[3];
        int ctr = 0;

        for (int i = 0; i < TILES_COUNTER; i++) {
            if (board[i].getType() == type) {
                tiles[ctr++] = board[i];
            }
        }

        ctr = 0;

        for (int i = 0; i < 3 && tiles[i] != null; i++) {
            if (tiles[i].hasOwner() && tiles[i].getOwner() == owner) {
                ctr++;
            }
        }

        return ctr;
    }

}
