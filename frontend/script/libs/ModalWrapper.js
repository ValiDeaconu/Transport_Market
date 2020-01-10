// Array which contains all the created modals using Modal Wrapper
// Used to assure that all modals are hidden, when a new modal is opened
var __modals_cache = [];

/**
 * ModalWrapper class, used to standardise a modal script
 * @author Valentin Deaconu
 */
class ModalWrapper {
    constructor(modalId, modalBtnId, modalCloseId) {
        var modal = document.getElementById(modalId);
        var button = document.getElementById(modalBtnId);
        var close = document.getElementById(modalCloseId);

        __modals_cache.push(modal);

        button.onclick = function() {
            modal.style.display = "block";

            __modals_cache.forEach(function(value, index) {
                if (value != modal) {
                    value.style.display = "none";
                }
            });
        }

        close.onclick = function() {
            modal.style.display = "none";
        }

        this.cache = {};
    }

    addFormItem(itemId, onChangeFunc) {
        var item = document.getElementById(itemId);
        
        if (item.value != "") {
            onChangeFunc();
        }
        
        item.onchange = function() {
            onChangeFunc();
        };
    }

    addButtonItem(itemId, onClickFunc) {
        var item = document.getElementById(itemId);
        item.onclick = onClickFunc;
    }

    addCacheItem(item, value) {
        this.cache[item] = value;
    }
}