package client.frame;

import javax.swing.table.DefaultTableModel;

public class GameRoomTableModel extends DefaultTableModel {

    public GameRoomTableModel() {
        addColumn("방 번호");
        addColumn("방 제목");
    }

    /**
     * JTable을 수정할 수 없게 함
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

}
