package project.entity.basic.datatable;

public enum DataRowState {
    //
    // 摘要:
    //     The row has not changed since System.Data.DataRow.AcceptChanges was last called.
    Unchanged,
    //
    // 摘要:
    //     The row has been added to a System.Data.DataRowCollection, and System.Data.DataRow.AcceptChanges
    //     has not been called.
    Added,
    //
    // 摘要:
    //     The row was deleted using the System.Data.DataRow.Delete method of the System.Data.DataRow.
    Deleted,
    //
    // 摘要:
    //     The row has been modified and System.Data.DataRow.AcceptChanges has not been
    //     called.
    Modified
}
